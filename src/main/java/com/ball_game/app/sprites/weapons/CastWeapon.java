package com.ball_game.app.sprites.weapons;

import java.awt.Graphics;

public class CastWeapon extends DrawableWeapon{

    double slope;
    double y_intercept;
    boolean move_positive;
    int size = 10;

    public CastWeapon(int x, int y, int momentum, int end_x, int end_y){
        super(x,y,momentum);
        System.out.println(String.format("starting weapon location %d, %d",x,y));
        find_move_directions(end_x, end_y);
    }

    public void draw(Graphics g){
        g.drawRoundRect(x, y, size, size, 3, 3);
    }

    public void update(){
        double temp_y = y;
        double temp_x = x;
        double xval = this.move_positive?(temp_x+=this.momentum):(temp_x-=this.momentum);
        temp_y = slope*xval + y_intercept;
        y = (int)temp_y;
        x = (int)temp_x;
    }

    private void find_move_directions(int end_x , int end_y){
        if (end_x - this.x == 0){
            slope = 0;
        } else {
            slope = ((double)end_y - (double)this.y) / ((double)end_x - (double)this.x);
        }
        move_positive = end_x > this.x? true : false;
        this.y_intercept = this.y - slope * this.x;
        System.out.println(String.format("y = %.2fx + %.2f", slope, y_intercept));
    }
}
