package com.rec;

public class check_entry {

    // Перебор
    static Boolean first(double[] array, double val){
        for (double el:array){
            if (val == el) {
                return true;
            }
        }
        return false;
    }

    // Двоичный поиск (рекурсия)
    public static Boolean second(double[] array, double val, int first, int last) {
        int middle = (first + last)/2;

        // Если вдруг неправильные границы
        if (last < first) {
            return null;
        }

        if (val == array[middle]) {
            return true;
        } else if (val < array[middle]) {
            return second(array, val, first, middle - 1);
        } else {
            return second(array, val, middle + 1, last);
        }
    }

    public static void main(String[] args){
        double[] array = new double[100000000];
        System.out.println(array.length);
        for (int i=0; i<array.length; i++){
            array[i] = Math.random()*100;
        }
        System.out.println(array[1]);
        // Замер времени первого метода
        long time1 = System.currentTimeMillis();
        System.out.println(check_entry.first(array, 15.221346188130502));
        System.out.println("Время первого метода = " + (System.currentTimeMillis() - time1));

        // Замер времени второго метода
        long time2 = System.currentTimeMillis();
        check_entry.second(array, 15.221346188130502, 1, array.length);
        System.out.println("Время второго метода = " + (System.currentTimeMillis() - time2));


    }
}
