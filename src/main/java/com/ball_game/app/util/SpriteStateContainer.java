package com.ball_game.app.util;

import java.util.ArrayList;
import java.util.Optional;

import com.ball_game.app.sprites.BaseSprite;
import com.ball_game.app.sprites.Character;

public class SpriteStateContainer {

    private static SpriteStateContainer INSTANCE;

    private static Character main_character;
    private static ArrayList<BaseSprite> sprites = new ArrayList<BaseSprite>();

    private SpriteStateContainer(){}

    public static SpriteStateContainer getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SpriteStateContainer();
        }
        return INSTANCE;
    }

    public void addMainCharacter(Character character){
        System.out.println("setting main character");
        main_character = character;
    }

    public void addSprite(BaseSprite sprite){
        sprites.add(sprite);
    }

    public Optional<BaseSprite> getSpriteByyName(String name){
        for(BaseSprite sprite : sprites){
            if (sprite.getName() == name){
                return Optional.of(sprite);
            }
        }
        return Optional.empty();
    }

    public Character getMainCharacter(){
        if (main_character == null){
            System.out.println("main character not added to stateContainer");
            System.exit(0);
        }
        return main_character;
    }

}
