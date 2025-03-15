package com.ball_game.app.util;

import javax.swing.JLabel;

import com.ball_game.app.sprites.Character;

public class InfoPanel extends JLabel {
    
    SpriteStateContainer stateContainer = SpriteStateContainer.getInstance();
    final String fmt_string =  "name: %s    health: %d    level: %d";
    Character character;
    private String char_name;
    private int char_health;
    private int char_level;

    public InfoPanel(){
        character = stateContainer.getMainCharacter();
        this.char_name = character.getName();
        this.char_health = character.getHealth();
        this.char_level = character.getLevel();
        setText(getPrintString());
    }

    public void updateHealth(){
        this.char_health = character.getHealth();
        super.setText(getPrintString());
    }

    public void update_level() {
        this.char_level = character.getLevel();
        super.setText(getPrintString());
    }

    private String getPrintString(){
        return String.format(
            fmt_string,
            char_name,
            char_health,
            char_level
            );
    }
}
