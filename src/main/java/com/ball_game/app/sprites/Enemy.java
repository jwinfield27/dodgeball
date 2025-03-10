package com.ball_game.app.sprites;

import java.awt.*;
import java.lang.Math;

import com.ball_game.app.ApiInterfaces.containers.EnemyDataContainer;
import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.util.SpriteStateContainer;

public class Enemy extends BaseSprite{

    private static int id_count = 0;

    int level;
    boolean weapon_ready;
    WeaponDataContainer wdc;
    Character character_ref;
    SpriteStateContainer stateContainer = SpriteStateContainer.getInstance();
    Weapon current_weapon = null;
    int size;
    int momentum = 1;

    public Enemy(double start_x, double start_y, EnemyDataContainer edc){
        super(edc.getName() +"-"+String.valueOf(id_count));
        this.character_ref = stateContainer.getMainCharacter();
        this.x = (int)start_x;
        this.y = (int)start_y;
        this.weapon_ready = true;
        this.size = edc.getSpriteSize();
        this.level = edc.getLevel();
        this.wdc = edc.getWeapon();
    }

    public void draw(Graphics g){
            g.drawOval(x, y, size, size);
            draw_weapon(g);
    }

    public void chase(){
        this.move();
        this.spawn_weapon();
    }

    private void move(){
        boolean move_left = this.character_ref.x < this.x ? true: false;
        boolean move_up = this.character_ref.y < this.y ? true: false;

        if(Math.abs(this.x - this.character_ref.x) > 100 || Math.abs(this.y - this.character_ref.y) > 100){
            this.x = move_left ? this.x - this.momentum: this.x + momentum;
            this.y = move_up ? this.y - this.momentum: this.y + momentum;
        }
    }

    private void spawn_weapon(){
        if (weapon_ready){
            createWeapon();
            weapon_ready = false;
        }
        if (current_weapon != null){
            current_weapon.update();
        }
    }

    private void createWeapon(){
        current_weapon = new Weapon(
                                    x+this.size/2,
                                    y+this.size/2,
                                    this.character_ref.x,
                                    this.character_ref.y,
                                    wdc.getLevel(),
                                    wdc.getDamage(),
                                    wdc.getName(),
                                    wdc.getWeapon_type()
                                    );
    }

    private void draw_weapon(Graphics g){
        if (current_weapon != null){
            current_weapon.draw(g);
            checkForDamage();
        } 
    }

    private void checkForDamage(){
        current_weapon.checkForDamage(character_ref);
    }

    public String get_weapon_type(){
        return this.wdc.getWeapon_type();
    }

    public Point get_weapon_position(){
        return this.current_weapon.getLocation();
    }

    public void cleanup_weapon(){
        System.out.println("weapon cleaned up!");
        this.current_weapon = null;
        this.weapon_ready = true;
    }

    public int getWeaponSize(){
        return this.current_weapon.getWeaponSize();
    }
}
