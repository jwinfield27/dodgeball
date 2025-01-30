package com.ball_game.app.sprites;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Character {

    public int x;
    public int y;
    int max_x;
    int max_y;
    int size;
    public int level;
    double momentum = 0.0;

    public Character(){}

    public Character(int main_frame_x, int main_frame_y, int init_size, int level){
        this.x = main_frame_x / 2;
        this.size = init_size;
        this.y = main_frame_y - (init_size * 2);
        this.max_x = main_frame_x;
        this.max_y = main_frame_y;
        this.level = level;
    }

    public void setNewLocation(int new_x, int new_y){
        x = new_x;
        y = new_y;
    }

    public void keyPressed(KeyEvent event){
        int e = event.getKeyCode();

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
        g.fillRect(x, y, size, size);
    }

    public Point getLocation(){
        return new Point(x,y);
    }
}
