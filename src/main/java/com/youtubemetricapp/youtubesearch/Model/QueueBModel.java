package com.youtubemetricapp.youtubesearch.Model;

import com.rabbitmq.client.*;
import com.youtubemetricapp.youtubesearch.Controller.QueueBController;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class QueueBModel {
    static private List<String> listQ2 = new ArrayList<>();

    QueueBModel()
    {
        listQ2.clear();
    }
    //static method needed to send all messages into Queue B
    static public List<String> getQueueB() throws JSONException, IOException, TimeoutException {
        Logger logger = LoggerFactory.getLogger(QueueBController.class);
        String QUEUE_NAME = "queueA";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("wgrkxzlx");
        factory.setPort(1883);
        factory.setPassword("3Z_cKVg7KkVFJjo88UKcuIWDNTuDcBR0");
        factory.setHost("toad.rmq.cloudamqp.com");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                false, false,false, null);
//        listQ2.clear();
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                String messageQ2 = message.replaceAll("(?i)telecom","telco");
                listQ2.add(messageQ2);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
        for (int i= 0; i < listQ2.size(); i++) {
            String messageQ2 = listQ2.get(i);
            channel.queueDeclare("queueB",false,false,false,null);
            channel.basicPublish("", "queueB", null, messageQ2.getBytes(StandardCharsets.UTF_8));
            logger.info("[!] Send '" + messageQ2 + "'");
        }
        return listQ2;
    }

}
