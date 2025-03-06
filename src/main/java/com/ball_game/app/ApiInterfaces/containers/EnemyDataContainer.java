package com.ball_game.app.ApiInterfaces.containers;

public class EnemyDataContainer {
    private String name;
    private int weaponId;
    private int sprite_size;
    private int level;
    private WeaponDataContainer weapon;

    public EnemyDataContainer(){}

    public EnemyDataContainer(String name, int weaponId, int sprite_size, int level) {
        this.name = name;
        this.weaponId = weaponId;
        this.sprite_size = sprite_size;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeaponId(){
        return this.weaponId;
    }

    public void setWeaponId(int weaponId){
        this.weaponId = weaponId;
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