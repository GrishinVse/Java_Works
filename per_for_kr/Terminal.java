package com.per_for_kr;

public class Terminal {
    static void Check(Card_month card){
        if (card.checkMonth()){
            System.out.println("* ГОРИТ ЗЕЛЕНЫЙ ЦВЕТ *");
            System.out.printf("Карта действует еще %d месяцев.", card.GetMonth());
        } else {
            System.out.println("* ГОРИТ КРАСНЫЙ ЦВЕТ *");
            System.out.println("Карта не активна!");
        }
    }

    static void Check(Card_oneride card){
        if (card.GetNumberOfRides() > 0){
            System.out.println("* ГОРИТ ЗЕЛЕНЫЙ ЦВЕТ *");
            card.MinusRide();
        } else {
            System.out.println("* ГОРИТ КРАСНЫЙ ЦВЕТ *");
            System.out.println("На карте нет больше поездок!");
        }
        System.out.printf("Осталось %d поездок | Баланс = %d", card.GetNumberOfRides(), card.GetBalance());
    }
}
