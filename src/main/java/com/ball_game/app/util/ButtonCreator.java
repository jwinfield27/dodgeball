package com.ball_game.app.util;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class ButtonCreator {
    public static JButton createButton(String label, String background, ActionListener action_listener){
        RGBTuple color_data = BackgroundConverter.getRGBTuple(background);
        Color new_color = new Color(color_data.r+50,color_data.g+50,color_data.b+50);
        
        JButton new_button = new JButton(label);
        new_button.setFocusable(true);
        new_button.setBackground(new_color);
        new_button.addActionListener(action_listener);
        return new_button;
    }
}
