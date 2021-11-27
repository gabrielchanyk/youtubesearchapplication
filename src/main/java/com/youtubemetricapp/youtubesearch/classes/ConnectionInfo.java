package com.youtubemetricapp.youtubesearch.classes;

//singleton class to keep connection information
public class ConnectionInfo {
    private ConnectionInfo() {}

    //heroku connection
//    static public String hostname = "toad.rmq.cloudamqp.com";
//    static public int port = 1883;
    static public String hostname = "localhost";
    static public int port = 8080;
    static public String uri = "amqps://wgrkxzlx:3Z_cKVg7KkVFJjo88UKcuIWDNTuDcBR0@toad.rmq.cloudamqp.com/wgrkxzlx";
}
