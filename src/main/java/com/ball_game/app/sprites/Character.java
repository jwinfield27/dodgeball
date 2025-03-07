package com.ball_game.app.sprites;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.ball_game.app.util.SwingData;

public class Character extends BaseSprite {

    public int level;
    public int health = 50;
    int max_x;
    int max_y;
    int size;
    double momentum = 0.0;
    SwingData swingData = SwingData.getInstance();


    public Character(String name, int init_size, int level){
        super(name);
        this.x = swingData.getX() / 2;
        this.size = init_size;
        this.y = swingData.getY() - (init_size * 2);
        this.max_x = swingData.getX();
        this.max_y = swingData.getY();
        this.level = level;
    }

    public void setNewLocation(int new_x, int new_y){
        x = new_x;
        y = new_y;
    }

    public void keyPressed(KeyEvent event){
        int e = event.getKeyCode();

        System.out.println("event code " + String.valueOf(e));

        switch(e){
            case KeyEvent.VK_W:
                y = y-5<0? y: y-5;
                break;
            case KeyEvent.VK_S:
                y = (y+5+size)<max_y? y+5: y;
                break;
            case KeyEvent.VK_D:
                x = (x+5+size)<max_x? x+5: x;
                break;
            case KeyEvent.VK_A:
                x = x-5<0? x: x-5;
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        if (health > 0){
            g.fillRect(x, y, size, size);
        }
    }

    public Point getLocation(){
        return new Point(x,y);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(String.format("current health %d", health));
    }
}
