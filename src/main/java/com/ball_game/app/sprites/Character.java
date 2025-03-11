package com.ball_game.app.sprites;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.util.SwingData;

public class Character extends BaseSprite {

    private static boolean w_pressed = false;
    private static boolean a_pressed = false;
    private static boolean s_pressed = false;
    private static boolean d_pressed = false;

    public int level;
    public int max_x;
    public int max_y;
    public int size;

    private SwingData swingData = SwingData.getInstance();
    private WeaponDataContainer weapon;

    private double momentum = 0.0;
    private Boolean weapon_ready = true;
    public int health = 50;

    public Character(String name, int init_size, int level){
        super(name);
        this.x = swingData.getX() / 2;
        this.size = init_size;
        this.y = swingData.getY() - (init_size * 2);
        this.max_x = swingData.getX();
        this.max_y = swingData.getY();
        this.level = level;
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
        this.changeCharacterDirection(e, false);
    }

    private void changeCharacterDirection(int e, boolean value){
        switch(e){
            case KeyEvent.VK_W:
                w_pressed = value;
                break;
            case KeyEvent.VK_S:
                w_pressed = value;
                break;
            case KeyEvent.VK_D:
                w_pressed = value;
                break;
            case KeyEvent.VK_A:
                w_pressed = value;
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        change_position();
        move_weapon();
        if (health > 0){
            g.fillRect(x, y, size, size);
        }
    }

    private void move_weapon(){}

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
        System.out.println(String.format("current health %d", health));
    }

    public void giveWeapon(WeaponDataContainer wdc){
        System.out.println("weapon given to player: " + wdc.getName());
        this.weapon = wdc;
    }
}
