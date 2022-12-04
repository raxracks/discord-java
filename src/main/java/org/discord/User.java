package org.discord;

public class User {
    public String id;
    public String username;
    public String discriminator;
    RestAPI api;

    public User(RestAPI api, String id, String username, String discriminator) {
        this.api = api;
        this.id = id;
        this.username = username;
        this.discriminator = discriminator;
    }
}
