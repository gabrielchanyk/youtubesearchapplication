package com.youtubemetricapp.youtubesearch.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.youtubemetricapp.youtubesearch.Model.classes.ConnectionInfo;
import com.youtubemetricapp.youtubesearch.Model.classes.Credentials;
import com.youtubemetricapp.youtubesearch.Model.classes.YtInfo;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//example url format is this https://www.youtube.com/watch?v=as_ZpcwtQnk
//this is youtube url + videoid
//using this logic will concatenate strings to make url link
//create list of xml

public class QueueAModel {

    static String query = "telecom";
    //static method needed to send all messages into Queue A
    static public List<String> getQueueA() throws JSONException, IOException, TimeoutException {

        List<String> ytQ1 = new ArrayList<>();
        List<Map<String,Object>> rawYt = YtApiService.getrawyt(Credentials.apikey,query);
        Logger logger = LoggerFactory.getLogger(QueueAModel.class);
        String QUEUE_NAME = "queueA";
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername(Credentials.connUser);
//        factory.setPassword(Credentials.connPw);
        factory.setHost(ConnectionInfo.hostname);
//        factory.setPort(ConnectionInfo.port);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i= 0; i < rawYt.size(); i++) {
            Map<String, Object> ytInfoMap = new ObjectMapper().convertValue(rawYt.get(i), Map.class);
            Map<String, String> idMap = new ObjectMapper().convertValue(ytInfoMap.get("id"), Map.class);
            Map<String, String> snippetMap = new ObjectMapper().convertValue(ytInfoMap.get("snippet"), Map.class);
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = xmlMapper.writeValueAsString(new YtInfo(String.format("https://www.youtube.com/watch?v=%s",idMap.get("videoId")), snippetMap.get("title")));
            channel.basicPublish("",QUEUE_NAME,null,xmlString.getBytes(StandardCharsets.UTF_8));
            logger.info("[!] Send '" + xmlString + "'");
            ytQ1.add(xmlString);
        }
        channel.close();
        connection.close();
        return ytQ1;
    }
}
