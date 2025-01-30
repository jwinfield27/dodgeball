package com.ball_game.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.ball_game.app.sprites.Character;
import com.ball_game.app.sprites.Enemy;
import com.ball_game.app.util.EnemyList;

/**
 * Unit test for simple App.
 */
public class AppTest {

    int x_length = 800;
    int y_length = 600;

    @Test
    public void EnemyPlacementTest(){
        int test_size = 50;
        int start_x = x_length/2;
        int start_y = y_length/2;
        EnemyList el = new EnemyList();
        Character test_character = new Character(x_length, y_length, test_size, el);
        Enemy test_enemy = new Enemy();


    }

}
