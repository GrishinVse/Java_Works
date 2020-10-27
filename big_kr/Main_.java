package com.big_kr;

import java.util.ArrayList;

public class Main_ {
    public static void main(String[] args){
        System.out.println("Start of KR!");

        Item i1 = new Item("Салат Цезарь", 199.50, "gramms", 300.0, "Овощной салат. Популярное блюдо американской кухни.", 1);
        Item i2 = new Item("Супчик Борщ", 100.50, "gramms", 250.0, "Самый лучший суп из свеклы!", 1);
        Item i3 = new Item("Коктель", 250.50, "ml", 350.0, "Безалкогольное Мохито", 2);

        ArrayList<Item> itemList = new ArrayList<Item>();

        itemList.add(i1);
        itemList.add(i2);
        itemList.add(i3);

        Menu main_menu = new Menu(itemList);
    }
}
