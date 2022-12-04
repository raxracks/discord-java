package org.discord;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.login("MTQxMDEyNjY1NTA0ODkwODgw.GFxWBt.9NKN2xBBvTbQWTQDcoWpVugFHiX1huh4luKX1M");

        client.getGuild("935192357811404800").getChannels().forEach(channel -> {
            System.out.println(channel.name);
        });
    }
}