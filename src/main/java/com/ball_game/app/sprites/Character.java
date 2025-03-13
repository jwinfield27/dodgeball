package com.ball_game.app.sprites;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.util.SwingData;

public class Character extends BaseActor {

    private static boolean w_pressed = false;
    private static boolean a_pressed = false;
    private static boolean s_pressed = false;
    private static boolean d_pressed = false;

    public int level;
    public int max_x;
    public int max_y;
    public int health;

    private SwingData swingData = SwingData.getInstance();

    public Character(String name, int init_size, int level, WeaponDataContainer wdc){
        super(name, init_size, wdc);
        this.x = swingData.getX() / 2;
        this.y = swingData.getY() - (init_size * 2);
        this.max_x = swingData.getX();
        this.max_y = swingData.getY();
        this.level = level;
        this.health = 50;
    }

    public int getLevel(){
        return this.level;
    }

    public void setNewLocation(int new_x, int new_y){
        x = new_x;
        y = new_y;
    }

    public void keyPressed(KeyEvent event){
        int e = event.getKeyCode();
        this.changeCharacterDirection(e, true);
    }

    public void keyReleased(KeyEvent event){
        int e = event.getKeyCode();
        System.out.println("key released" + event.toString());
        this.changeCharacterDirection(e, false);
    }

    private void changeCharacterDirection(int e, boolean value){
        switch(e){
            case KeyEvent.VK_W:
                w_pressed = value;
                System.out.println("w pressed");
                break;
            case KeyEvent.VK_S:
                s_pressed = value;
                System.out.println("s pressed");
                break;
            case KeyEvent.VK_D:
                d_pressed = value;
                System.out.println("d pressed");
                break;
            case KeyEvent.VK_A:
                a_pressed = value;
                System.out.println("a pressed");
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        change_position();
        this.spawn_weapon();
        if (health > 0){
            g.fillRect(x, y, size, size);
        }
    }

    private void change_position(){
        if(w_pressed){ y = y-5<0? y: y-5; }
        if(a_pressed){ x = x-5<0? x: x-5; }
        if(s_pressed){ y = (y+5+size)<max_y? y+5: y; }
        if(d_pressed){ x = (x+5+size)<max_x? x+5: x; }
    }

    public Point getLocation(){
        return new Point(x,y);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
