package com.ball_game.app.Screens;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;

import com.ball_game.app.ApiInterfaces.ApiTransaction;
import com.ball_game.app.ApiInterfaces.containers.SpriteGsonContainer;
import com.ball_game.app.ApiInterfaces.containers.SpritesDataContainer;
import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;
import com.ball_game.app.sprites.Character;
import com.ball_game.app.util.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private InfoPanel info_panel;
    private final int DELAY = 15;
    private Timer timer;
    Character main_character;
    SpriteStateContainer spriteStateContainer = SpriteStateContainer.getInstance();
    SwingData swingData = SwingData.getInstance();
    EnemyList enemy_list = new EnemyList();
    SpriteGsonContainer background;
    File background_file;

    public GamePanel(){
        getBackgroundImages();
        WeaponDataContainer character_weapon = null;
        ApiTransaction<WeaponDataContainer> wdc = new ApiTransaction<WeaponDataContainer>(
            "get",
            "/weapon/random/character",
            WeaponDataContainer.class
        );
        character_weapon = wdc.execute();
        main_character = new Character("test",50, 5, character_weapon);
        spriteStateContainer.addMainCharacter(main_character);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                main_character.lastClickLocation(e);
            }
        });
        
        setPreferredSize(new Dimension(swingData.getX(), swingData.getY()));
        info_panel = new InfoPanel();
        this.add(info_panel);
        enemy_list.createEnemies();
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
    public void keyReleased(KeyEvent e){
        int ekc = e.getKeyCode();

        if (ekc==KeyEvent.VK_W || ekc==KeyEvent.VK_A || ekc==KeyEvent.VK_D || ekc==KeyEvent.VK_S){
            main_character.keyReleased(e);
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(background_file), 0, 0, null);
        } catch (IOException e){
            System.out.println("could not load background image");
            System.exit(0);
        }
        main_character.draw(g);
        enemy_list.draw(g);
        enemy_list.chase();
        info_panel.updateHealth();
        checkEntityHealth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void checkEntityHealth(){
        if (main_character.getHealth() == 0) {
            launchEndScreen();
        }
        if (enemy_list.checkForAllEnemiesEliminated()){
            enemy_list.createEnemies();
        }
    }

    private void launchEndScreen(){
        System.out.println("game ended");
        System.exit(0);
    }

    private void getBackgroundImages(){
        background =  new ApiTransaction<ArrayList<SpriteGsonContainer>>(
            "get",
            "/sprites/contains/background",
            SpritesDataContainer.getType()
        ).execute().get(0);
        String file_name = background.name;
        background_file = new File(file_name+".jpg");
        try (FileOutputStream fileOutputStream = new FileOutputStream(background_file)){
            fileOutputStream.write(background.getImageData());
        } catch (IOException e){
            System.exit(0);
        }
    }
}
