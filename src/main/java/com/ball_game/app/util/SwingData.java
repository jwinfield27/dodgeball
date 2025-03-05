package com.ball_game.app.util;

public class SwingData {

    private static SwingData instance;

    private static int x_length = 800;
    private static int y_length = 600;

    private SwingData(){}

    public static SwingData getInstance(){
        if (instance == null){
            instance = new SwingData();
        }
        return instance;
    }

    public int getX(){
        return x_length;
    }

    public int getY(){
        return y_length;
    }

}
