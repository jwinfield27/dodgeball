package com.ball_game.app.sprites;

import java.awt.Graphics;
import java.awt.Point;

import com.ball_game.app.sprites.weapons.*;

public class Weapon extends BaseSprite {

    private final int damage_reset_cooldown_threshold = 25;

    private static int id_count = 0;

    private int x;
    private int y;
    private int momentum = 5;
    private int end_x = 0;
    private int end_y = 0;
    private int level;
    private int damage;
    private int damage_reset_cooldown_count;
    private String weapon_type;
    private boolean delt_damage = false;
    private DrawableWeapon draw_weapon;

    public Weapon(int caster_x, int caster_y, int character_x, int character_y, int level, int damage, String name, String weapon_type) {
        super(name+"-"+String.valueOf(id_count));
        id_count++;
        this.x = caster_x;
        this.y = caster_y;
        this.end_x = character_x;
        this.end_y = character_y;
        this.level = level;
        this.damage = damage;
        this.weapon_type = weapon_type;
        findDrawType();
    }

    private void findDrawType(){
        this.draw_weapon = switch (weapon_type){
            case "cast" -> new CastWeapon(x,y,momentum, end_x, end_y);
            default -> throw new IllegalArgumentException(
                String.format("unsupported weapon type found %s", weapon_type));
        };
    }

    public void update(){
        draw_weapon.update();
    }

    public void draw(Graphics g){
        draw_weapon.draw(g);
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return draw_weapon.getLocation();
    }

    public int getWeaponSize(){
        return draw_weapon.size;
    }

    public boolean hasDealtDamage(){
        return delt_damage;
    }

    public void setDeltDamage(boolean delt_damage){
        this.delt_damage = delt_damage;
        if (this.delt_damage) {
            this.damage_reset_cooldown_count = this.damage_reset_cooldown_threshold;
        }
    }

    public void decreaseCooldowns(){
        if (this.damage_reset_cooldown_count > 0){
            this.damage_reset_cooldown_count--;
        }
        if (this.damage_reset_cooldown_count == 0){
            this.delt_damage = false;
        }
    }

    public void checkForDamage(Character character_ref){
        Point weapon_location = this.getLocation();
        Point character_location = character_ref.getLocation();

        int weapon_x = (int)weapon_location.getX();
        int weapon_y = (int)weapon_location.getY();

        int end_weapon_x = weapon_x + getWeaponSize();
        int end_weapon_y = weapon_y + getWeaponSize();

        int character_x = (int)character_location.getX();
        int character_y = (int)character_location.getY();

        int end_char_x = character_x + character_ref.size;
        int end_char_y = character_y + character_ref.size;

        boolean takes_damage = true;

        if(end_weapon_x < character_x || end_char_x < weapon_x){
            takes_damage = false;
        }
        else if (end_weapon_y < character_y || end_char_y < weapon_y){
            takes_damage = false;
        }

        if(takes_damage && !this.hasDealtDamage()){
            character_ref.takeDamage(level);
            this.setDeltDamage(true);
        }
        else {
            this.decreaseCooldowns();
        }
    }
}
