package org.discord;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RestAPI {
    private static final Logger logger = LogManager.getLogger("RestAPI");
    OkHttpClient httpClient = new OkHttpClient();
    Client client;
    String baseURL = "https://discord.com/api/v9/";
    Conversion conversion = new Conversion(this);

    public RestAPI(Client client) {
        this.client = client;
    }

    public ArrayList<Message> getChannelMessages(String channelId, int limit) {
        ArrayList<Message> messages = new ArrayList<Message>();

        Request request = new Request.Builder()
                .url(baseURL + "channels/" + channelId + "/messages?limit=" + limit)
                .header("authorization", client.token)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            JSONArray messagesJSON = new JSONArray(response.body().string());

            messagesJSON.forEach(message -> {
                JSONObject messageObj = (JSONObject)message;

                Message messageInstance = conversion.toMessage(messageObj);

                messages.add(messageInstance);
            });
        } catch(Exception e) {
            logger.error("Could not fetch messages for channel");
        }

        return messages;
    }

    public Channel getChannel(String channelId) {
        Channel channel = new Channel(this);

        Request request = new Request.Builder()
                .url(baseURL + "channels/" + channelId)
                .header("authorization", client.token)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            JSONObject channelObj = new JSONObject(response.body().string());

            channel = conversion.toChannel(channelObj);
        } catch(Exception e) {
            logger.error("Could not fetch channel");
        }

        return channel;
    }

    public void sendMessage(String channelId, String content) {
        Request request = new Request.Builder()
                .url(baseURL + "channels/" + channelId + "/messages")
                .header("authorization", client.token)
                .post(RequestBody.create("{\"content\": \"" + content + "\"}", MediaType.get("application/json; charset=utf-8")))
                .build();

        try {
            httpClient.newCall(request).execute();
        } catch(Exception e) {
            logger.error("Could not send message");
        }
    }

    public Guild getGuild(String guildId) {
        Guild guild = new Guild(this);

        Request request = new Request.Builder()
                .url(baseURL + "guilds/" + guildId)
                .header("authorization", client.token)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            JSONObject guildObj = new JSONObject(response.body().string());

            guild = conversion.toGuild(guildObj);
        } catch(Exception e) {
            logger.error("Could not fetch guild");
        }

        return guild;
    }

    public ArrayList<Channel> getChannels(String guildId) {
        ArrayList<Channel> channels = new ArrayList<Channel>();

        Request request = new Request.Builder()
                .url(baseURL + "guilds/" + guildId + "/channels")
                .header("authorization", client.token)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            JSONArray channelsJSON = new JSONArray(response.body().string());

            channelsJSON.forEach(channel -> {
                JSONObject channelObj = (JSONObject)channel;

                if(channelObj.getNumber("type").equals(0)) {
                    Channel channelInstance = conversion.toChannel(channelObj);

                    channels.add(channelInstance);
                }
            });
        } catch(Exception e) {
            logger.error("Could not fetch guild channels");
        }

        return channels;
    }
}
