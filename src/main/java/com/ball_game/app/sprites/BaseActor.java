package com.ball_game.app.sprites;

import java.awt.*;

import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.util.SwingData;

public class BaseActor extends BaseSprite{

    protected int health;
    protected Weapon current_weapon;
    protected WeaponDataContainer wdc;
    protected int size;
    protected boolean weapon_ready;
    protected int momentum;
    protected int level;

    private SwingData swingData = SwingData.getInstance();

    BaseActor(){}

    BaseActor(String name, int size, int momentum, WeaponDataContainer wdc){
        super(name);
        this.size = size;
        this.wdc = wdc;
        this.health = 50;
        this.weapon_ready = true;
        this.momentum = momentum;
        this.level = 5;
    } 

    public void update_weapon(){
        if (weapon_ready){
            createWeapon();
            weapon_ready = false;
        }
        else if (current_weapon != null){
            current_weapon.update();
        }

        Point weapon_loc = this.current_weapon.getLocation();
        if (
            weapon_loc.x < 0 ||
            weapon_loc.y < 0 ||
            weapon_loc.x > swingData.getX() ||
            weapon_loc.y > swingData.getY()
        ){
            this.cleanup_weapon();
        }
    }

    protected void createWeapon() {
        boolean is_enemy = this.getClass() == Enemy.class;
        current_weapon = new Weapon(
            is_enemy,
            x+this.size/2,
            y+this.size/2,
            wdc.getLevel(),
            wdc.getDamage(),
            wdc.getName(),
            wdc.getWeapon_type()
        );
    }


    protected void draw_weapon(Graphics g){
        if (current_weapon != null){
            current_weapon.draw(g);
            checkForDamage();
        } 
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public Point get_weapon_position(){
        return this.current_weapon.getLocation();
    }

    public void cleanup_weapon(){
        this.current_weapon = null;
        this.weapon_ready = true;
    }

    public int getWeaponSize(){
        return this.current_weapon.getWeaponSize();
    }

    public String get_weapon_type(){
        return this.wdc.getWeapon_type();
    }

    public int getLevel(){
        return this.level;
    }

    public int getHealth(){
        return this.health;
    }

    public int getSize(){
        return this.size;
    }

    private void checkForDamage(){
        current_weapon.checkForDamage();
    }
}
