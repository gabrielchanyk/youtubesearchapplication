package com.youtubemetricapp.youtubesearch.classes;

//singleton class to keep connection information
public class ConnectionInfo {
    private ConnectionInfo() {}

    //heroku connection
//    static public String hostname = "toad.rmq.cloudamqp.com";
//    static public int port = 1883;
    static public String hostname = "localhost";
    static public int port = 8080;
    static public String uri = "amqps://orrpsvph:A8hC7CNmo2fkUMNB0iztjPpJVB6qhHH5@woodpecker.rmq.cloudamqp.com/orrpsvph";
}
