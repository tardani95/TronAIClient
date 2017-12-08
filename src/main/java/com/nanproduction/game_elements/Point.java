package com.nanproduction.game_elements;

import com.nanproduction.communication.GameState;

import java.util.Random;

public class Point {
    private int x = 0;
    private int y = 0;

    public Point() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void initRand() {
        Random rand = new Random();
        this.x = rand.nextInt(GameState.MAP_SIZE_X);
        this.y = rand.nextInt(GameState.MAP_SIZE_Y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            return x==((Point) obj).getX() && y==((Point) obj).getY();
        }
        return super.equals(obj);
    }

    public int manhattanDistance(Point other){
        return Math.abs(other.getX()-x)+Math.abs(other.getY()-y);
    }

    void move(eDirection dir){
        switch (dir)//fej koordináták
        {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            default:
                break;
        }

    }

    boolean outOfBorder(){
        return x >= GameState.MAP_SIZE_X || x < 0 || y >= GameState.MAP_SIZE_Y || y < 0;
    }

}
