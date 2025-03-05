package com.ball_game.app.Screens;

import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel implements KeyListener {
    
    JTextField player_code = new JTextField();

    public SettingsPanel(){
        player_code.addActionListener(new SubmitTextEntry());
        this.add(player_code);
    }

    @Override
    public void keyPressed(KeyEvent ke){

    }

    @Override
    public void keyReleased(KeyEvent ke){

    }

    @Override
    public void keyTyped(KeyEvent ke){

    }

    class SubmitTextEntry implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){

        }
    }


}
