package com.ball_game.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Optional;
import java.awt.*;

import com.ball_game.app.sprites.Enemy;
import com.ball_game.app.sprites.Character;
import com.ball_game.app.ApiInterfaces.ApiTransaction;
import com.ball_game.app.ApiInterfaces.containers.EnemyDataContainer;
import com.ball_game.app.ApiInterfaces.containers.WeaponDataContainer;

public class EnemyList {
    
    List<Enemy> enemies = new ArrayList<>();
    SwingData swingData = SwingData.getInstance();
    UtilFunctions util = new UtilFunctions(swingData.getX(), swingData.getY());
    SpriteStateContainer spriteStateContainer = SpriteStateContainer.getInstance();
    Random rand = new Random();
    Character main_character = spriteStateContainer.getMainCharacter();

    int max_enemies = 1;

    public Enemy[] createEnemies(){
        int num_of_new_enemies = max_enemies;
        EnemyDataContainer new_enemy_data;
        Enemy new_enemy;
        for(int i = 0; i < num_of_new_enemies; i++){
            new_enemy_data = this.findEnemyData();
            new_enemy = createEnemy(new_enemy_data);
            spriteStateContainer.addSprite(new_enemy);
            enemies.add(new_enemy);
        }
        Enemy[] en = new Enemy[enemies.size()];
        return enemies.toArray(en);
    }

    private EnemyDataContainer findEnemyData(){
        Optional<EnemyDataContainer> new_enemy_data_option = getEnemyInfoByLevel(main_character.getLevel());
        EnemyDataContainer new_enemy_data;
        if (new_enemy_data_option.isPresent()){
            new_enemy_data = new_enemy_data_option.get();
        }
        else {
            new_enemy_data = this.expandEnemySearch(new_enemy_data_option);
        }
        return new_enemy_data;
    }

    private EnemyDataContainer expandEnemySearch(Optional<EnemyDataContainer> new_enemy_data){
        int level =main_character.getLevel();
        while (new_enemy_data.isEmpty()){
            level+=1;
            new_enemy_data = getEnemyInfoByLevel(level);
        }
        return new_enemy_data.get();
    }

    private Optional<EnemyDataContainer> getEnemyInfoByLevel(int level){
        ApiTransaction<EnemyDataContainer> enemy_container = new ApiTransaction<EnemyDataContainer>(
            "get",
            "/enemy/level/random/" + String.valueOf(level),
            EnemyDataContainer.class
        );
        EnemyDataContainer edc;
        WeaponDataContainer weapon;
        try{
            edc = enemy_container.execute();

        } catch(Exception e){
            System.out.println("error executing Enemy data get request");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

        try{
            weapon = getWeaponDataByLevel(edc.getLevel()).get();
            System.out.println(weapon.getName());
            edc.setWeapon(weapon);
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("error getting or setting weapon data");
            System.exit(1);
        }
        return Optional.of(edc);
    }

    private Optional<WeaponDataContainer> getWeaponDataByLevel(int level){
        ApiTransaction<WeaponDataContainer> weapon_container = new ApiTransaction<WeaponDataContainer>(
            "get",
            "/weapon/random/enemy/" + String.valueOf(level),
            WeaponDataContainer.class
        );
        
        WeaponDataContainer wdc;
        try{
            wdc = weapon_container.execute();
            return Optional.of(wdc);
        } catch (Exception e){
            return Optional.empty();
        }

    }

    private Enemy createEnemy(EnemyDataContainer edc){
        // find position in a quadrant where the player isnt
        Point main_char_loc = main_character.getLocation();
        Point spawn_location = util.findEnemySpawn(main_char_loc.x, main_char_loc.y);
        Enemy new_enemy = new Enemy(spawn_location.x,spawn_location.x,edc);
        return new_enemy;
    }

    public List<Enemy> getArrayList(){
        return enemies;
    }

    public void chase(){
        for (Enemy e : enemies){
            e.chase();
        }
    }

    public void draw(Graphics g){
        for (Enemy e : enemies){
            e.draw(g);
        }
    }

    public boolean checkForAllEnemiesEliminated(){
        this.enemies = this.enemies.stream().filter(e -> e.getHealth() > 0).toList();
        if (this.enemies.size() != 0){
            return false;
        }
        return true;
    }
}
