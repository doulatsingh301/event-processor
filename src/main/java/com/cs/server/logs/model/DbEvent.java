package com.cs.server.logs.model;

public class DbEvent {


    private String id;
    private long duration;
    private String type;
    private String host;
    private boolean alert;

    public DbEvent(String id, long duration, String type, String host, boolean alert){
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "id:" +  id + " duration:" + duration + " type:" + type + " host:" + host + " alert:" + alert;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
