package com.rec;

public class rec_num {
    static String show(int x){
        if (x == 1){
            return "1";
        }
        return show(x - 1) + " | "+ x;
    }

    public static void main(String[] args){
        System.out.print(rec_num.show(7));
    }
}
