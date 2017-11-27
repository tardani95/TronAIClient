package com.nanproduction;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {

    public static final int MAP_SIZE_X = 50;
    public static final int MAP_SIZE_Y = 35;
    public static final Color[] COLORS = new Color[]{Color.BLUE, Color.GREEN, Color.YELLOW, Color.BROWN, Color.BLACK, Color.CYAN, Color.BEIGE};

    private static Game instance = new Game();

    private static List<Player> players = new ArrayList<>();

    private Achievement achievement;
    private MainWindowController controller;

    public static List<Player> getPlayers() {
        return players;
    }

    public Achievement getAchievement() {
        return achievement;
    }


    private Game() {
    }

    public static Game getInstance() {
        return instance;

    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public void init(MainWindowController controller) {
        this.controller = controller;
        start();
    }

    public void refreshGameState(String message) {
        if(message.charAt(0)!='{'){

            return;
        }

        Gson gson=new Gson();
        JsonObject jsonObject=  gson.fromJson(message, JsonObject.class);
        JsonArray playersJson=jsonObject.getAsJsonArray("players");

        //String s = playersJson.toString();

        players=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            players = mapper.readValue(playersJson.toString(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class,
                            Player.class));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Player[] tmplayer=gson.fromJson(playersJson,Player[].class);
        // players=

        JsonElement achievementJson = jsonObject.get("achievements");
        achievement = gson.fromJson(achievementJson, Achievement.class);
    }


    public void run() {

        /*TODO - Pick your AI code here!




         */
        stop();
    }

}
