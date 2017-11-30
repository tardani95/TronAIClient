package com.nanproduction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;

import static javafx.scene.input.KeyCode.*;


public class Player{



    public static final KeyCode[][] KEY_CUTS = {
            {A, LEFT, J, NUMPAD4},
            {D, RIGHT,L, NUMPAD6},
            {W, UP,I, NUMPAD8},
            {S, DOWN,K, NUMPAD5}
    };
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("gameOver")
    @Expose
    private boolean gameOver;
    @JsonProperty("name")
    private String name;
    @SerializedName("ready")
    @Expose
    private volatile boolean ready;
    @SerializedName("head")
    @Expose
    private Point head;
    @SerializedName("tail")
    @Expose
    private List<Point> tail;
    @SerializedName("dir")
    @Expose
    private eDirection dir;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("keyCode")
    @Expose
    private KeyCode keyCode;

    public Color getColor() {
        return Color.web(color);
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }


    public boolean isReady() {
        return ready;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Point getHead() {
        return head;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Point> getTail() {
        return tail;
    }

    public eDirection getDir() {
        return dir;
    }

    Player() {
    }


    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }




}
