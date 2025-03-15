package com.ball_game.app.sprites;

import java.awt.*;
import java.lang.Math;

import com.ball_game.app.ApiInterfaces.containers.EnemyDataContainer;
import com.ball_game.app.util.SpriteStateContainer;

public class Enemy extends BaseActor {

    private static int id_count = 0;

    int level;
    Character character_ref;
    SpriteStateContainer stateContainer = SpriteStateContainer.getInstance();

    public Enemy(int start_x, int start_y, EnemyDataContainer edc){
        super(
            edc.getName() +"-"+String.valueOf(id_count),
            edc.getSpriteSize(),
            3,
            edc.getWeapon());
        this.character_ref = stateContainer.getMainCharacter();
        this.x = (int)start_x;
        this.y = (int)start_y;
        this.level = edc.getLevel();
    }

    public void draw(Graphics g){
            g.drawOval(x, y, size, size);
            draw_weapon(g);
    }

    public void chase(){
        this.move();
        update_weapon();
    }

    private void move(){
        boolean move_left = this.character_ref.x < this.x ? true: false;
        boolean move_up = this.character_ref.y < this.y ? true: false;

        if(Math.abs(this.x - this.character_ref.x) > 100 || Math.abs(this.y - this.character_ref.y) > 100){
            this.x = move_left ? this.x - this.momentum: this.x + momentum;
            this.y = move_up ? this.y - this.momentum: this.y + momentum;
        }
    }
}
