package com.big_kr;

/* Система позволяет вбивать блюда как по кухне, так и по бару
   У каждого проданного товара есть характеристики: цена за штуку товара,
   единица измерения, количество в единице измерения, состав */


public class Item {
    private String type;
    private String name;
    private Double prise; // цена за штуку товара
    private String unit; // единица измерения
    private Double amount; // количество в единице измерения
    private String composition; // состав

    public Item(String name, Double prise, String unit, Double amount, String composition, int type){
        this.name = name;
        this.prise = prise;
        this.unit = unit.toLowerCase();
        this.amount = amount;
        this.composition = composition;
        switch (type) {
            case 1:
                this.type = "BAR";
                break;
            case 2:
                this.type = "KITCHEN";
                break;
        }
    }

    public String getName() {
        return name;
    }
}
