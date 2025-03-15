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

    private SwingData swingData = SwingData.getInstance();

    public Character(String name, int init_size, int level, WeaponDataContainer wdc){
        super(name, init_size, 3, wdc);
        this.x = swingData.getX() / 2;
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
                s_pressed = value;
                break;
            case KeyEvent.VK_D:
                d_pressed = value;
                break;
            case KeyEvent.VK_A:
                a_pressed = value;
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        change_position();
        update_weapon();
        draw_weapon(g);
        if (health > 0){
            g.fillRect(x, y, size, size);
        }
    }

    private void change_position(){
        if(w_pressed){ y = y-momentum<0? y: y-momentum; }
        if(a_pressed){ x = x-momentum<0? x: x-momentum; }
        if(s_pressed){ y = (y+momentum+size)<max_y? y+momentum: y; }
        if(d_pressed){ x = (x+momentum+size)<max_x? x+momentum: x; }
    }

    public Point getLocation(){
        return new Point(x,y);
    }
}
