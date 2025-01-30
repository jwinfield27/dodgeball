package com.ball_game.app.ApiInterfaces.errors;

public class InvalidRestVerbError extends Exception{
    public InvalidRestVerbError(String errorMessage){
        super(errorMessage);
    }
}
