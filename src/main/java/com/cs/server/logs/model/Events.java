package com.cs.server.logs.model;

public class Events {

    private String id;
    private String state;
    private String type;
    private String host;
    private long timestamp;

    public Events(String id, String state, long timestamp){
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }

    public Events(String id, String state, long timestamp, String type, String host){
        this(id, state, timestamp);
        this.type = type;
        this.host = host;
    }

    @Override
    public String toString() {
        return "id:" +  id + " state:" + state + " type:" + type + " host:" + host + " timestamp:" + timestamp;
    }

    public String getId(){
        return this.id;
    }

    public String getState(){
        return this.state;
    }

    public String getType(){
        return this.type;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public String getHost(){
        return this.host;
    }
}
