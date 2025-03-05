package com.ball_game.app.util;

import javax.swing.JLabel;

public class InfoPanel extends JLabel {
    
    final String fmt_string =  "name: %s    health: %d    level: %d";
    private String char_name;
    private int char_health;
    private int char_level;

    public InfoPanel(String char_name, int char_health, int char_level){
        this.char_name = char_name;
        this.char_health = char_health;
        this.char_level = char_level;
        setText(getPrintString());
    }

    public void updateHealth(int new_health){
        this.char_health = new_health;
        super.setText(getPrintString());
    }

    public void update_level(int new_level) {
        this.char_level = new_level;
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
