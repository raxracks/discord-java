package org.discord;

import org.json.JSONObject;

public class Conversion {
    RestAPI api;

    public Conversion(RestAPI api) {
        this.api = api;
    }

    public Channel toChannel(JSONObject channelObj) {
        return new Channel(this.api,
                channelObj.getString("id"),
                channelObj.getString("name"),
                channelObj.getString("last_message_id"));
    }

    public User toUser(JSONObject userObj) {
        return new User(this.api,
                userObj.getString("id"),
                userObj.getString("username"),
                userObj.getString("discriminator"));
    }

    public Message toMessage(JSONObject messageObj) {
        JSONObject authorObj = messageObj.getJSONObject("author");

        return new Message(this.api,
                toUser(authorObj),
                messageObj.getString("id"),
                messageObj.getString("content"));
    }

    public Guild toGuild(JSONObject guildObj) {
        return new Guild(this.api,
                guildObj.getString("id"),
                guildObj.getString("name"));
    }
}
