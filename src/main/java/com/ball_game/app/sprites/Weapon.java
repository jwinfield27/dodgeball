package com.ball_game.app.sprites;

import java.awt.Graphics;
import java.awt.Point;

import com.ball_game.app.sprites.weapons.*;

public class Weapon extends BaseSprite {

    private final int damage_reset_cooldown_threshold = 25;

    private static int id_count = 0;

    private int momentum = 5;
    private int level;
    private int damage;
    private int end_x = -1;
    private int end_y = -1;
    private int damage_reset_cooldown_count;
    private String weapon_type;
    private boolean is_enemy;
    private boolean delt_damage = false;
    private DrawableWeapon draw_weapon;

    public Weapon(boolean is_enemy, int caster_x, int caster_y, int end_x, int end_y, int level, int damage, String name, String weapon_type){
        super(name+"-"+String.valueOf(id_count));
        this.end_x = end_x;
        this.end_y = end_y;
        id_count++;
        this.level = level;
        this.damage = damage;
        this.is_enemy = is_enemy;
        this.weapon_type = weapon_type;
        findDrawType(caster_x, caster_y, is_enemy);
    }

    public Weapon(boolean is_enemy, int caster_x, int caster_y, int level, int damage, String name, String weapon_type) {
        super(name+"-"+String.valueOf(id_count));
        id_count++;
        this.level = level;
        this.damage = damage;
        this.is_enemy = is_enemy;
        this.weapon_type = weapon_type;
        findDrawType(caster_x, caster_y, is_enemy);
    }

    private void findDrawType(int x, int y, boolean is_enemy){
        this.draw_weapon = switch (weapon_type){
            case "cast" -> new CastWeapon(x,y, this.end_x, this.end_y, momentum, is_enemy);
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

    public Point getEndLocation(){
        return new Point(end_x,end_y);
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

    public boolean has_end_loc_set(){
        return this.end_x != -1 && this.end_y != -1;
    }

    public void decreaseCooldowns(){
        if (this.damage_reset_cooldown_count > 0){
            this.damage_reset_cooldown_count--;
        }
        if (this.damage_reset_cooldown_count == 0){
            this.delt_damage = false;
        }
    }

    public BaseActor getActorToDamage(){
        if (this.is_enemy){
            return this.spriteStateContainer.getMainCharacter();
        }
        return this.spriteStateContainer.getSprites().get(0);
    }

    public void checkForDamage(){
        Point weapon_location = this.getLocation();
        BaseActor actor_to_damage = this.getActorToDamage();
        Point actor_to_damage_loc = actor_to_damage.getLocation();

        int end_weapon_x = weapon_location.x + getWeaponSize();
        int end_weapon_y = weapon_location.y + getWeaponSize();

        int character_x = actor_to_damage_loc.x;
        int character_y = actor_to_damage_loc.y;

        int end_char_x = character_x + actor_to_damage.size;
        int end_char_y = character_y + actor_to_damage.size;

        boolean takes_damage = true;

        if(end_weapon_x < character_x || end_char_x < weapon_location.x){
            takes_damage = false;
        }
        else if (end_weapon_y < character_y || end_char_y < weapon_location.y){
            takes_damage = false;
        }

        if(takes_damage && !this.hasDealtDamage()){
            actor_to_damage.takeDamage(damage);
            this.setDeltDamage(true);
        }
        else {
            this.decreaseCooldowns();
        }
    }
}
