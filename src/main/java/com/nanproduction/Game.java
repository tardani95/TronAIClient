package com.nanproduction;



import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {

    public static final int MAP_SIZE_X = 50;
    public static final int MAP_SIZE_Y = 35;
    private static Game instance = new Game();

    private static List<Player> players = new ArrayList<>();
    private Achievement achievement;
    private MainWindowController controller;



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



    public void run() {

        /*TODO - Pick your AI code here!




         */
        stop();
    }

}
