package com.ball_game.app.util;

import java.awt.Point;
import java.util.Random;

public class UtilFunctions {

    Random random = new Random();
    int screen_x;
    int screen_y;

    public UtilFunctions(int x, int y){
        screen_x = x;
        screen_y = y;
    }
    
    public Point findEnemySpawn(int entity_x, int entity_y){
        /*
         * Zone 1 top left
         * Zone 2 top right
         * Zone 3 bottom left
         * Zone 4 bottom right
         */
        int half_screen_x = screen_x / 2;
        int half_screen_y = screen_y / 2;
        
        int player_quadrant = findPlayableCharacterQuadrant(entity_x, entity_y, half_screen_x, half_screen_y);
        return findSpawnPoint(player_quadrant);
    }

    private int findPlayableCharacterQuadrant(int entity_x, int entity_y, int half_screen_x, int half_screen_y){
        if (entity_x > half_screen_x) {
            return (entity_y > half_screen_y)? 2: 4;
        }
        else{
            return (entity_y > half_screen_y)? 1: 3;
        }
    }

    private Point findSpawnPoint(int player_quadrant){
        int new_quad = player_quadrant;
        while (new_quad == player_quadrant){
            new_quad = random.nextInt(player_quadrant);
        }
        return switch (player_quadrant) {
            case 1 -> findRandLocationInQuadrant(1, screen_x/2, 1, screen_y/2);
            case 2 -> findRandLocationInQuadrant(screen_x/2, screen_x, 1, screen_y/2);
            case 3 -> findRandLocationInQuadrant(screen_x/2, screen_x, screen_y/2, screen_y);
            default -> findRandLocationInQuadrant(1, screen_x/2, screen_y/2, screen_y);
        };
    }

    private Point findRandLocationInQuadrant(int min_x, int max_x, int min_y, int max_y){
        return new Point(
            random.nextInt(max_x-min_x) + min_x,
            random.nextInt(max_y-min_y) + min_y
        );
    }
        
}
