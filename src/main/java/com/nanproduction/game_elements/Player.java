package com.nanproduction.game_elements;

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
    protected int id;
    @SerializedName("gameOver")
    @Expose
    protected boolean gameOver;
    @JsonProperty("name")
    protected String name;
    @SerializedName("ready")
    @Expose
    protected volatile boolean ready;
    @SerializedName("head")
    @Expose
    protected Point head;
    @SerializedName("tail")
    @Expose
    protected List<Point> tail;
    @SerializedName("dir")
    @Expose
    protected eDirection dir;
    @SerializedName("score")
    @Expose
    protected int score;
    @SerializedName("color")
    @Expose
    protected String color;
    @SerializedName("keyCode")
    @Expose
    protected KeyCode keyCode;

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

    public Player() {
    }


    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }



    public eDirection makeDecision(){



        //TODO - Pick your code here















        return null;
    }




}
