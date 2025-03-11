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
    Random rand = new Random();
    Character main_character;

    int max_enemies = 1;

    public Enemy[] createEnemies(Character main_character){
        this.main_character = main_character;
        int num_of_new_enemies = max_enemies;
        EnemyDataContainer new_enemy_data;
        Enemy new_enemy;
        for(int i = 0; i < num_of_new_enemies; i++){
            new_enemy_data = this.findEnemyData();
            new_enemy = createEnemy(new_enemy_data);
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
            "/enemy/level/random/5",
            EnemyDataContainer.class
        );
        EnemyDataContainer edc;
        int needed_weapon_id;
        WeaponDataContainer weapon;
        try{
            edc = enemy_container.execute();
            System.out.println(edc.getWeaponId());

        } catch(Exception e){
            System.out.println("error executing Enemy data get request");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

        try{
            needed_weapon_id = edc.getWeaponId();
            weapon = getWeaponDataById(needed_weapon_id).get();
            edc.setWeapon(weapon);
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("error getting or setting weapon data");
            System.exit(1);
        }
        return Optional.of(edc);
    }

    private Optional<WeaponDataContainer> getWeaponDataById(int id){
        String get_weapon_base_string = "/weapon/" + String.valueOf(id);
        ApiTransaction<WeaponDataContainer> weapon_container = new ApiTransaction<WeaponDataContainer>(
            "get",
            get_weapon_base_string,
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
        Point spawn_location = util.findEnemySpawn((int)main_char_loc.getX(),(int)main_char_loc.getY());
        Enemy new_enemy = new Enemy(spawn_location.getX(),spawn_location.getY(),edc);
        return new_enemy;
    }

    public List<Enemy> getArrayList(){
        return enemies;
    }

    public void chase(){
        for (Enemy e : enemies){
            e.chase();
        }
        for (Enemy e : enemies){
            String weapon_type = e.get_weapon_type().strip();
            if (weapon_type.equals("cast")){
                Point weapon_position = e.get_weapon_position();
                int weapon_x = (int)weapon_position.getX();
                int weapon_y = (int)weapon_position.getY();
                if (weapon_x < 0 || weapon_y < 0 || weapon_x > util.screen_x || weapon_y > util.screen_y){
                    e.cleanup_weapon();
                }
            }
        }
    }

    public void draw(Graphics g){
        for (Enemy e : enemies){
            e.draw(g);
        }
    }
}
