package com.ball_game.app.ApiInterfaces.containers;

public class WeaponDataContainer {
    private int level;
    private String name;
    private int damage;
    private int momentum;
    private String weapon_type;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(String weapon_type) {
        this.weapon_type = weapon_type;
    }

    public int getMomentum(){
        return this.momentum;
    }

    public void setMomentum(int momentum){
        this.momentum = momentum;
    }
}
