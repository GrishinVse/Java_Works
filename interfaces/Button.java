package com.interfaces;

public class Button {
    EventHandler eventHandler;
    public Button(EventHandler eventHandler){
        this.eventHandler = eventHandler;
    }

    public void click(){
        eventHandler.execute();
    }
}
