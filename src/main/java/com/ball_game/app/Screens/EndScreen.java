package com.ball_game.app.Screens;

import javax.swing.*;


public class EndScreen extends JFrame{

    public EndScreen(){
        EndPanel ep = new EndPanel();
        add(ep);
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
