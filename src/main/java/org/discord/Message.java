package org.discord;

public class Message {
    public User author;
    public String id;
    public String content;
    RestAPI api;

    public Message(RestAPI api, User author, String id, String content) {
        this.api = api;
        this.author = author;
        this.id = id;
        this.content = content;
    }
}
