package com.nanproduction.game_elements;

public class Achievement {
    private Point coord;

    Achievement(Point coord){
        this.coord=coord;
    }

    public Point getCoord(){
        return coord;
    }


    public int getX(){return coord.getX();}

    public int getY(){return  coord.getY();}

}
