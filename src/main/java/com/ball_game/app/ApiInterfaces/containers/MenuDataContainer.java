package com.ball_game.app.ApiInterfaces.containers;

public class MenuDataContainer extends TransactionContainer{
    String title;
    String backgroundColor;
    String buttonTheme;
    String version;

    public MenuDataContainer(){}

    public MenuDataContainer(String title, String backgroundColor, String buttonTheme, String version){
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.buttonTheme = buttonTheme;
        this.version = version;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBackgroundColor(){
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor){
        this.backgroundColor = backgroundColor;
    }

    public String getButtonTheme(){
        return this.buttonTheme;
    }

    public void setButtonTheme(String buttonTheme){
        this.buttonTheme = buttonTheme;
    }

    public String getVersion(){
        return this.version;
    }
    public void setVersion(String version){
        this.version = version;
    }

    public String toString(){
        return String.format("\n%s\n%s\n%s\n%s", this.title, this.backgroundColor, this.buttonTheme, this.version);
    }
}
