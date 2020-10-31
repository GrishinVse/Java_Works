package com.per_for_kr;

import java.util.Scanner;

public class Card_month {
    private String type;
    private int payment;
    private int num_of_month;

    public Card_month(String str){
        if (str.toLowerCase().equals("социальная")){
            this.type = "социальная";
            this.payment = 99;
        } else if (str.toLowerCase().equals("школьная")){
            this.type = "школьная";
            this.payment = 149;
        } else if (str.toLowerCase().equals("студенческая")){
            this.type = "студенческая";
            this.payment = 199;
        }
    }

    public Card_month(){
        this.type = "взрослый";
        this.payment = 299;
    }

    public int getPayment() {
        return this.payment;
    }

    public String getType(){
        return this.type;
    }

    public int PayForCard(int money){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество месяцев : ");
        int num = scanner.nextInt();
        int final_price = (this.payment*num);
        System.out.printf("У вашей карточки тип ' %s ' \n Оплата на %d месяцев = %d \n", this.type, num, final_price);

        if (final_price <= money){
            System.out.println("Транзакция прошла успешно!");
            this.num_of_month = num;
            return (money - final_price);
        } else {
            System.out.println("Ошибка! Нехватка денежных средств!");
            return money;
        }
    }

    public boolean checkMonth(){
        return (this.GetMonth() > 0);
    }

    public int GetMonth(){
        return this.num_of_month;
    }
}
