package com.ball_game.app.ApiInterfaces.containers;

public class EnemyDataContainer {
    private String name;
    private int sprite_size;
    private int level;
    private WeaponDataContainer weapon;

    public EnemyDataContainer(){}

    public EnemyDataContainer(String name, int sprite_size, int level) {
        this.name = name;
        this.sprite_size = sprite_size;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponDataContainer getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponDataContainer weapon) {
        this.weapon = weapon;
    }

    public int getSpriteSize() {
        return sprite_size;
    }

    public void setSpriteSize(int size) {
        this.sprite_size = size;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}