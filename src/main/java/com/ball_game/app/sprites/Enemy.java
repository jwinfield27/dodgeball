package com.ball_game.app.sprites;

import java.awt.*;

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

    public void chase(int character_x, int character_y){
        boolean move_left = character_x < this.x ? true: false;
        boolean move_up = character_y < this.y ? true: false;

        this.x = move_left ? this.x - this.momentum: this.x + momentum;
        this.y = move_up ? this.y - this.momentum: this.y + momentum;

        if (weapon_ready){
            createWeapon(character_x, character_y);
            weapon_ready = false;
        }
        if (current_weapon != null){
            current_weapon.update();
        }
    }


    private void createWeapon(int character_x, int character_y){
        current_weapon = new Weapon(
                                    x,
                                    y,
                                    character_x,
                                    character_y,
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
