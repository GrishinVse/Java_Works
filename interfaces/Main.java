package com.interfaces;

public class Main {
    public static void main(String[] args){
        Button button = new Button(new ButtonClickHandler());
        button.click();

        Button button1 = new Button(new EventHandler() ){
            private boolean on = false;
            @Override
            public void execute() {
                if (on){
                    System.out.println("ВЫКЛЮЧИЛИ");
                } else {
                    System.out.println("ВКЛЮЧИЛИ");
                }
                on = !on;
            }
        };

        Button button2 = new Button(new EventHandler()){
            @Override
            public void execute() {
                System.out.println("ЗАПУСТИЛИ ПЕЧАТЬ");
            }
        };
        button1.click();
        button2.click();
        button1.click();
    }
}
