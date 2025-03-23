package com.ball_game.app.sprites.weapons;

import java.awt.Graphics;
import java.awt.Point;

import com.ball_game.app.sprites.BaseActor;
import com.ball_game.app.util.SpriteStateContainer;

public class CastWeapon extends DrawableWeapon{

    SpriteStateContainer spriteStateContainer = SpriteStateContainer.getInstance();

    double velx;
    double vely;
    boolean is_enemy;
    int size = 10;

    public CastWeapon(int x, int y, int momentum, boolean is_enemy){
        super(x,y,momentum);
        this.is_enemy = is_enemy;
        set_move_vector();
    }

    public void draw(Graphics g){
        g.drawRoundRect(x, y, size, size, 3, 3);
    }

    public void update(){
        x += velx;
        y += vely;
    }

    private void set_move_vector(){
        int wep_x = this.x;
        int wep_y = this.y;

        BaseActor target = get_target();
        Point target_loc = target.getLocation();
        int char_x = target_loc.x + target.getSize()/2;
        int char_y = target_loc.y + target.getSize()/2;

        double vX = char_x - wep_x;
        double vY = char_y - wep_y;
        double distance = Math.sqrt(vX * vX + vY * vY);

        velx = vX / distance * momentum;
        vely = vY / distance * momentum;
    }

    private BaseActor get_target(){
        BaseActor target;
        if (is_enemy){
            target = spriteStateContainer.getMainCharacter();
        } else {
            target = spriteStateContainer.getSprites().get(0);
        }
        return target;
    }
}
