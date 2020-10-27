package com.big_kr;

/* отмечать какие блюда поданы / отображать информацию о заказе по столику / закрывать заказ
   Система оформляет заказ по столам, выбивает итоговую сумму заказа
*/

import java.util.HashMap;
import java.util.Scanner;

public class Order {
    final String in = "взят";
    final String out = "подан";
    final String deny = "отклонен";

    private int table_num;
    private Double total_prise;
    private String state; // строка в которой будет отображаться состояние заказа
    private HashMap<Item, String> order_map = new HashMap<>();

    public Order(int table){
        this.table_num = table;
        this.total_prise = 0.0;
        this.state = "OPEN";
        this.order_map = new HashMap<>();
    }

    public void MakeOrder(Menu menu){
        for (int i = 0; i < menu.getMenu_list().size(); i++) {
            System.out.printf(" Номер %d =  ", i+1);
            System.out.print(menu.getMenu_list().get(i) + "\n");
        }
        System.out.println("Введите номер товара :");
        Scanner scanner = new Scanner(System.in);
        int item_index = scanner.nextInt();

        Item some_item = menu.getMenu_list().get(item_index);

        // Если у нас есть предмет в стоплисте то мы его не добавляем
        if (!menu.getStop_list().contains(some_item)){
            this.AddToOrder(some_item);
        } else {
            System.out.println("Товар в стоп-листе");
        }
    }

    public void AddToOrder(Item item){
        this.order_map.put(item, in);
    }

    public void Close(){
        this.state = "CLOSE";
    }



}
