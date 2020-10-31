package com.per_for_kr;

public class Card_oneride {
    private final int ride_prise = 40;

    private int num_ride = 0;
    private int balance = 0;

    public Card_oneride(int rides){
        this.num_ride = rides;
    }

    public Card_oneride(int rides, int rubs){
        this.num_ride = rides;
        this.balance = rubs;
    }

    // Добавляем поездки
    public void AddRide(int num){
        this.num_ride += num;
    }

    // Добавляем деньги
    public int AddMoney(int money) throws Exception {
        if (money < 0){
            throw new Exception("Неправильная операция");
        } else {
            this.balance += money;
            // Происходит автоматическая покупки поездки
            int nums = (money / ride_prise);
            this.AddRide(nums);
            return (money - (ride_prise*nums));
        }
    }

    public int GetBalance(){
        return this.balance;
    }

    public void MinusRide(){
        this.num_ride = this.num_ride - 1;
    }

    public int GetNumberOfRides(){
        return this.num_ride;
    }
}
