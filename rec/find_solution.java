package com.rec;

public class find_solution {

    public static double find_x(int start, int end){
        double x = start;
        while ( ((Math.cos(Math.pow(x, 5)) + Math.pow(x, 4) + x * (-345.3) -23) != 0 ) & (x <= end)){
            x += 0.0000001;
        }
        return x;
    }

    public static void main(String[] args){
        System.out.println("Поиск решения уравнения cos(x^5) + x^4 - 345.3*x - 23 = 0");
        System.out.println(find_solution.find_x(0, 10));
    }
}
