package com.ball_game.app.Screens;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;

import com.ball_game.app.ApiInterfaces.ApiTransaction;
import com.ball_game.app.ApiInterfaces.containers.SpriteGsonContainer;
import com.ball_game.app.ApiInterfaces.containers.SpritesDataContainer;
import com.ball_game.app.ApiInterfaces.errors.InvalidRestVerbError;
import com.ball_game.app.sprites.Character;
import com.ball_game.app.util.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private InfoPanel info_panel;
    private final int DELAY = 15;
    private Timer timer;
    Character main_character;
    EnemyList enemy_list = new EnemyList(SwingData.getInstance().getX(), SwingData.getInstance().getY());
    SpriteGsonContainer background;
    File background_file;

    public GamePanel(){
        getBackgroundImages();
        main_character = new Character(SwingData.getInstance().getX(), SwingData.getInstance().getY(),
                                        50, 5);
        setPreferredSize(new Dimension(SwingData.getInstance().getX(), SwingData.getInstance().getY()));
        info_panel = new InfoPanel("test_player", main_character.health, main_character.level);
        this.add(info_panel);
        enemy_list.createEnemies(main_character);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int ekc = e.getKeyCode();

        if (ekc==KeyEvent.VK_W || ekc==KeyEvent.VK_A || ekc==KeyEvent.VK_D || ekc==KeyEvent.VK_S){
            main_character.keyPressed(e);
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
        // try {
        //     g.drawImage(ImageIO.read(background_file), 0, 0, null);
        // } catch (IOException e){
        //     System.out.println("could not load background image");
        //     System.exit(0);
        // }
        main_character.draw(g);
        enemy_list.draw(g);
        enemy_list.chase();
        info_panel.updateHealth(main_character.health);
        checkPlayerHealth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void checkPlayerHealth(){
        if (main_character.health == 0) {
            launchEndScreen();
        }
    }

    private void launchEndScreen(){
        System.out.println("game ended");
        System.exit(0);
    }

    private void getBackgroundImages(){
        try{
            background =  new ApiTransaction<ArrayList<SpriteGsonContainer>>(
                "get",
                ApiData.getInstance().getHost() + "/sprites/contains/arblights2",
                SpritesDataContainer.getType()
                ).execute().get(0);
            String file_name = background.name;
            background_file = new File(file_name+".jpg");
            try (FileOutputStream fileOutputStream = new FileOutputStream(background_file)){
                fileOutputStream.write(background.getImageData());
            } catch (IOException e){
                System.exit(0);
            }
        } catch(InvalidRestVerbError e){
            System.out.println("error loading background image");
        }
        
    }
}
