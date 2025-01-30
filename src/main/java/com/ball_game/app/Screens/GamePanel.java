package com.ball_game.app.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ball_game.app.sprites.Character;
import com.ball_game.app.util.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    int x_dem = 800;
    int y_dem = 600;
    private final int DELAY = 25;
    private Timer timer;
    Character main_character;
    EnemyList enemy_list = new EnemyList(x_dem, y_dem);

    public GamePanel(){
        main_character = new Character(x_dem, y_dem, 50, 5);
        setPreferredSize(new Dimension(x_dem, y_dem));
        enemy_list.createEnemies(main_character);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int ekc = e.getKeyCode();

        if (ekc==KeyEvent.VK_W || ekc==KeyEvent.VK_A || ekc==KeyEvent.VK_D || ekc==KeyEvent.VK_S){
            main_character.keyPressed(e);
            enemy_list.chase(main_character.x, main_character.y);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        main_character.draw(g);
        enemy_list.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
