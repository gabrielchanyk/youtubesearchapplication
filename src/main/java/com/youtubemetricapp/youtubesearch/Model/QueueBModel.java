package com.youtubemetricapp.youtubesearch.Model;

import com.rabbitmq.client.*;
import com.youtubemetricapp.youtubesearch.Controller.QueueBController;
import com.youtubemetricapp.youtubesearch.classes.ConnectionInfo;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class QueueBModel {
    static private List<String> listQ2 = new ArrayList<>();

    //constructor to clear list
    QueueBModel()
    {
        listQ2.clear();
    }
    //static method needed to send all messages into Queue B
    static public List<String> getQueueB() throws JSONException, IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        Logger logger = LoggerFactory.getLogger(QueueBController.class);
        //consume Queue A
        String QUEUE_NAME = "queueA";
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(ConnectionInfo.hostname);
        factory.setUri(ConnectionInfo.uri);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                false, false,false, null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                //replace "telecom" with "telco" in title
                String messageQ2 = message.replaceAll("(?i)telecom","telco");
                //add to list to send to Queue B
                listQ2.add(messageQ2);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
        //send xml messages in list to Queue B
        for (int i= 0; i < listQ2.size(); i++) {
            String messageQ2 = listQ2.get(i);
            channel.queueDeclare("queueB",false,false,false,null);
            channel.basicPublish("", "queueB", null, messageQ2.getBytes(StandardCharsets.UTF_8));
            logger.info("[!] Send '" + messageQ2 + "'");
        }
        //return list to show up on restapi page
        return listQ2;
    }

}
