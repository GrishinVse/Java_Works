package com.interfaces;

public class Calculatable {
    default int sumAll(int x, int y){
        return sum(x, y);
    }
    default int sumAll(int x, int y, int z){
        return sum(x,y,z);
    }

    private int sum(int... args){
        int summ = 0;
        for (int i: args){
            summ += 1;
        }
        return summ;
    }
}
