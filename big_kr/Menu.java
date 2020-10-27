package com.big_kr;

import java.util.ArrayList;
import java.util.Scanner;

/*  Система должна позволять создать меню / изменять меню / добавлять блюда в стоп лист
    (блюда которые присутствуют в меню, но отсутствуют сегодня по какой-то причине) /
    отмечать какие блюда поданы / отображать информацию о заказе по столику / закрывать заказ
*/

public class Menu {
    private ArrayList<Item> menu_list;
    private ArrayList<Item> stop_list;

    public Menu(Item dish){
        this.menu_list.add(dish);
        this.stop_list = new ArrayList<>();
    }

    public Menu(ArrayList<Item> dish_list){
        this.menu_list = dish_list;
        this.stop_list = new ArrayList<>();
    }

    public ArrayList<Item> getMenu_list() {
        return menu_list;
    }

    public ArrayList<Item> getStop_list() {
        return stop_list;
    }

    public void AddItem(Item item){
        if (this.menu_list.contains(item)){
            System.out.printf("Хотите обновить информацию о товаре %s ? \n Введите 'Y' или 'N'", item.getName());
            Scanner scanner = new Scanner(System.in);
            String agree = scanner.nextLine();
            if (agree.toLowerCase().equals("y")){
                int index = this.menu_list.indexOf(item);
                this.menu_list.set(index, item);
            } else if (agree.toLowerCase().equals("n")) {
                System.out.println("Объект меню остался без изменения");
            }
        } else {
            this.menu_list.add(item);
        }
    }

    public void DeleteItem(Item item){
        if (this.menu_list.contains(item)){
            this.menu_list.remove(item);
            if (this.stop_list.contains(item)) { this.stop_list.remove(item); }
        } else {
            System.out.printf("Некорректная операция! Объекта %s нет в меню!", item.getName());
        }

    }

    public void AddToStop(Item item){
        if (this.menu_list.contains(item)){
            this.stop_list.add(item);
        } else {
            System.out.printf("Некорректная операция! Объекта %s нет в меню!", item.getName());
        }
    }

}
