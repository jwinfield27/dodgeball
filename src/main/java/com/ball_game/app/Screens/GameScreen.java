package com.ball_game.app.Screens;

import javax.swing.*;

public class GameScreen extends JFrame{
    
    int game_screen_max_x = 800;
    int game_screen_max_y = 600;

    public GameScreen(){
        GamePanel gp = new GamePanel();
        add(gp);
        addKeyListener(gp);
        // don't allow the user to resize the window
        setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        pack();
        // open window in the center of the screen
        setLocationRelativeTo(null);
        // display the window
        setVisible(true);
    }
}
