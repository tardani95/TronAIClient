package com.nanproduction.communication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nanproduction.game_elements.Achievement;
import com.nanproduction.game_elements.Player;
import com.nanproduction.game_elements.Point;
import com.nanproduction.game_elements.eDirection;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameState {

    public static final int MAP_SIZE_X = 50;
    public static final int MAP_SIZE_Y = 35;
    public static final Color[] COLORS = new Color[]{Color.BLUE, Color.GREEN, Color.YELLOW, Color.BROWN, Color.BLACK, Color.CYAN, Color.BEIGE};

    private static GameState instance = new GameState();

    public static List<Player> players = new ArrayList<>();

    private Achievement achievement;
    private MainWindowController controller;

    public int myPlayerId;

    public List<Point> freeCoords=new ArrayList<>();

    public static List<Player> getPlayers() {
        return players;
    }

    public Achievement getAchievement() {
        return achievement;
    }




    private GameState() {
    }

    public static GameState getInstance() {
        return instance;

    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public void init(MainWindowController controller) {
        this.controller = controller;

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


        players.removeIf(Player::isGameOver);

        freeCoords.clear();
        for(int x=0; x<MAP_SIZE_X; x++){
            for(int y=0; y<MAP_SIZE_Y; y++){
                freeCoords.add(new Point(x,y));
            }
        }
        for(Player player:players){
            freeCoords.remove(player.getHead());
            for(Point tailPoint: player.getTail()){
                freeCoords.remove(tailPoint);
            }
        }
        freeCoords.remove(achievement.getCoord());


        Player player = null;
        for(Player myPlayer:players){
            if(myPlayer.getId()==myPlayerId){
                player=myPlayer;
                break;
            }
        }

        if(player!=null && !player.isGameOver()){
            sendDirection(player.makeDecision());

















            //TODO - Make Decision
        }



    }

    public void sendDirection(eDirection direction){
        switch (direction){
            case UP:
                MainWindowController.webSocket.sendText("8");
                System.out.println(8);
                break;
            case DOWN:
                MainWindowController.webSocket.sendText("2");
                System.out.println(2);
                break;
            case LEFT:
                MainWindowController.webSocket.sendText("4");
                System.out.println(4);
                break;
            case RIGHT:
                MainWindowController.webSocket.sendText("6");
                System.out.println(6);
                break;
            default:break;
        }
    }





}
