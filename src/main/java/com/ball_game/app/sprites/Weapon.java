package com.ball_game.app.sprites;

import java.awt.Graphics;
import java.awt.Point;

import com.ball_game.app.sprites.weapons.*;

public class Weapon {

    private final int damage_reset_cooldown_threshold = 25;

    private int x;
    private int y;
    private int momentum = 5;
    private int end_x = 0;
    private int end_y = 0;
    private int level;
    private int damage;
    private int damage_reset_cooldown_count;
    private String name;
    private String weapon_type;
    private boolean delt_damage = false;
    private DrawableWeapon draw_weapon;

    public Weapon(int caster_x, int caster_y, int character_x, int character_y, int level, int damage, String name, String weapon_type) {
        this.x = caster_x;
        this.y = caster_y;
        this.end_x = character_x;
        this.end_y = character_y;
        this.level = level;
        this.damage = damage;
        this.name = name;
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
}
