package com.ball_game.app.sprites.weapons;

import java.awt.Graphics;
import java.awt.Point;

public class  DrawableWeapon {

    protected int x;
    protected int y;
    protected int momentum;

    public DrawableWeapon(int x, int y, int momentum) {
        this.x = x;
        this.y = y;
        this.momentum = momentum;
    }

    public void draw(Graphics g){
        throw new UnsupportedOperationException("draw function must be overridden");
    }

    public void update(){
        throw new UnsupportedOperationException("Update function must be overridden");
    }

    public Point getLocation(){
        return new Point(x,y);
    }
}
