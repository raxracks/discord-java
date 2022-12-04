package org.discord;

import java.util.ArrayList;

public class Guild {
    public String id;
    public String name;

    RestAPI api;

    public Guild(RestAPI api) {
        this.api = api;
    }

    public Guild(RestAPI api, String id, String name) {
        this.api = api;
        this.id = id;
        this.name = name;
    }

    public ArrayList<Channel> getChannels() {
        return api.getChannels(this.id);
    }
}
