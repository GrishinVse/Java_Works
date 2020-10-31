package com.per_for_kr;

public class Main {
    public static void main(String[] args){
        Card_month my_card = new Card_month("Студенческая");
        System.out.println(my_card.getType());
        System.out.println(my_card.getPayment());
        ATM.start(my_card, 300);
    }
}
