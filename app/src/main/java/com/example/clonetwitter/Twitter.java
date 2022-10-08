package com.example.clonetwitter;

public class Twitter {
    private String username;
    private String disName;
    private String twitterMessage;
    private String time;

    public Twitter(String username, String disName, String twitterMessage, String time) {
        this.username = username;
        this.disName = disName;
        this.twitterMessage = twitterMessage;
        this.time = time;
    }
    public Twitter(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getTwitterMessage() {
        return twitterMessage;
    }

    public void setTwitterMessage(String twitterMessage) {
        this.twitterMessage = twitterMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Twitter{" +
                "username='" + username + '\'' +
                ", disName='" + disName + '\'' +
                ", twitterMessage='" + twitterMessage + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
