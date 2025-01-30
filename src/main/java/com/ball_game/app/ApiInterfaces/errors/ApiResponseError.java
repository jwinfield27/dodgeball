package com.ball_game.app.ApiInterfaces.errors;

public class ApiResponseError extends Exception {
    public ApiResponseError(String errorMessage){
        super(errorMessage);
    }
}
