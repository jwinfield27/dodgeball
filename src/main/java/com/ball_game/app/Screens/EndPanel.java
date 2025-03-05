package com.ball_game.app.Screens;

import javax.swing.JPanel;
import java.awt.event.*;
import com.ball_game.app.util.*;

public class EndPanel extends JPanel {

    public EndPanel(){
        ButtonCreator.createButton("RETRY", "dark_blue", new RestartGameButtonPress());
    }

    class RestartGameButtonPress implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
