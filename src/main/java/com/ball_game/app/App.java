package com.ball_game.app;

import com.ball_game.app.Screens.*;
import com.ball_game.app.ApiInterfaces.ApiTransaction;
import com.ball_game.app.ApiInterfaces.containers.MenuDataContainer;
import com.ball_game.app.ApiInterfaces.errors.InvalidRestVerbError;
import com.ball_game.app.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {

    int x_length = 800;
    int y_length = 600;
    JFrame frame;
    JPanel panel;
    JLabel menu_label;

    GameScreen gameScreen;

    public App() {
        frame = new JFrame();
        panel = new JPanel();
        createAndShowGui();
    }

    private JButton[] createButtons(String background){
        JButton[] button_list = {
            ButtonCreator.createButton("PLAY", background, new SwitchToGameListener()),
            ButtonCreator.createButton("SETTINGS", background, new SwitchToSettingsListener()),
            ButtonCreator.createButton("QUIT", background, new ExitListener()),            
        };
        return button_list;
    }

    private void createAndShowGui() {
        ApiTransaction<MenuDataContainer> menu_data = new ApiTransaction<MenuDataContainer>(
                                                        "get",
                                                        "http://127.0.0.1:8080/menu",
                                                        MenuDataContainer.class
                                                        );
        MenuDataContainer menu_data_container;
        try {
            menu_data_container = menu_data.execute();
            menu_label = new JLabel(menu_data_container.getBackgroundColor());
            menu_label.setHorizontalAlignment(SwingConstants.CENTER);
            frame = new JFrame(menu_data_container.getTitle());
            frame.add(menu_label);

            panel.setLayout(new GridBagLayout());
            RGBTuple color_data = BackgroundConverter.getRGBTuple(menu_data_container.getBackgroundColor());
            panel.setBackground(new Color(color_data.r, color_data.g, color_data.b));

            JButton[] menu_buttons = createButtons(menu_data_container.getBackgroundColor());
            for(JButton jb: menu_buttons){
                panel.add(jb);
            }
        }
        catch (InvalidRestVerbError e){
            System.err.println(e);
        }
        frame.add(panel);
        frame.setSize(x_length, y_length);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class SwitchToGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            gameScreen = new GameScreen();
            frame.setVisible(false);
        }
    }

    class SwitchToSettingsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("loading game settings");
        }
    }

    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Exiting the game");
        }
    }

    public static void main(String[] args) throws Exception {
        App new_app = new App();
    }
}

