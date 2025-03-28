package com.ball_game.app.sprites;

import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.util.SpriteStateContainer;
import java.awt.*;


public abstract class BaseSprite {

    int x;
    int y;
    int size;
    WeaponDataContainer wdc;
    SpriteStateContainer spriteStateContainer = SpriteStateContainer.getInstance();
    String name;

    BaseSprite(){}

    BaseSprite(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void draw(Graphics g){
        g.drawRect(x, y, 50, 40);
    }

    public Point getLocation(){
        return new Point(x,y);
    }
}
