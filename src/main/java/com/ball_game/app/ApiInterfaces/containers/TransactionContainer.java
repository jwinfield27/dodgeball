package com.ball_game.app.ApiInterfaces.containers;

import java.lang.reflect.*;

public class TransactionContainer {

    public String toString(){
        String ret_string = "";
        for (Field field: this.getClass().getDeclaredFields()){
            try{
                ret_string.concat(field.getName() + ": ");
                ret_string.concat(field.get(field).toString());
                ret_string.concat("\n");
            }
            catch(IllegalArgumentException|IllegalAccessException e){
                continue;
            }
        }
        return ret_string;
    }
}
