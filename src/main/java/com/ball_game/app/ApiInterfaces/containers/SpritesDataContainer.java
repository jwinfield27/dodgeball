package com.ball_game.app.ApiInterfaces.containers;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public class SpritesDataContainer extends TransactionContainer {

    private static Type spriteListType = new TypeToken<ArrayList<SpriteGsonContainer>>(){}.getType();

    List<SpriteGsonContainer> sprites;

    SpritesDataContainer(){}

    SpritesDataContainer(List<SpriteGsonContainer> sprites){
        this.sprites = sprites;
    }

    public void setSprites(List<SpriteGsonContainer> sprites){
        this.sprites = sprites;
    }

    public List<SpriteGsonContainer> getSprites(){
        return this.sprites;
    }

    public static Type getType(){
        return spriteListType;
    }
}
