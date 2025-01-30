package com.ball_game.app.util;

public class RGBTuple {
 
    String theme;
    public int r;
    public int g;
    public int b;

    public RGBTuple(){
        this.theme = "None";
    }

    public RGBTuple(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
        this.theme = ""; // TODO
    }
}
