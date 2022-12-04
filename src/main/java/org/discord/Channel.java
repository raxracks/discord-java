package org.discord;

public class Channel {
    public String id;
    public String name;
    public String lastMessageId;
    RestAPI api;

    public Channel(RestAPI api) {
        this.api = api;
    }

    public Channel(RestAPI api, String id, String name, String lastMessageId) {
        this.api = api;
        this.id = id;
        this.name = name;
        this.lastMessageId = lastMessageId;
    }

    public void send(String content) {
        api.sendMessage(this.id, content);
    }
}
