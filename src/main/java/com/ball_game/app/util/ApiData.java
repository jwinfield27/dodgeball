package com.ball_game.app.util;

public class ApiData {
    private static ApiData instance;

    private static String api_ip = "10.0.0.19";
    private static String port = "8080";

    private ApiData(){}

    public static ApiData getInstance() {
        if (instance == null) {
            instance = new ApiData();
        }
        return instance;
    }

    public String getHost(){
        return "http://" + api_ip + ":" + port;
    }
}
