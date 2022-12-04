package org.discord;

public class Client {
    public String token;
    RestAPI api;

    public void login(String token) {
        this.token = token;
        api = new RestAPI(this);
    }

    public Channel getChannel(String channelId) {
        return api.getChannel(channelId);
    }

    public Guild getGuild(String guildId) {
        return api.getGuild(guildId);
    }
}
