package com.ball_game.app.util;

import java.util.ArrayList;
import java.util.Optional;

import com.ball_game.app.sprites.BaseActor;
import com.ball_game.app.sprites.Character;

public class SpriteStateContainer {

    private static SpriteStateContainer INSTANCE;

    private static Character main_character;
    private static ArrayList<BaseActor> sprites = new ArrayList<BaseActor>();

    private SpriteStateContainer(){}

    public static SpriteStateContainer getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SpriteStateContainer();
        }
        return INSTANCE;
    }

    public void addMainCharacter(Character character){
        main_character = character;
    }

    public void addSprite(BaseActor sprite){
        sprites.add(sprite);
    }

    public ArrayList<BaseActor> getSprites(){
        return sprites;
    }

    public Optional<BaseActor> getSpriteByyName(String name){
        for(BaseActor sprite : sprites){
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
