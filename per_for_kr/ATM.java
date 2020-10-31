package com.per_for_kr;

public class ATM {
    static void start(Card_month card, int money){
        int change = card.PayForCard(money);
        System.out.printf("Остаток от операции %d", change);
    }

    static void start(Card_oneride card, int money) throws Exception {
        int change = card.AddMoney(money);
        System.out.printf("Остаток от операции %d", change);
    }
}
