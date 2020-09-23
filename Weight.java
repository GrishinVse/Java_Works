package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Weight {
    int[] nums;
    int[] nums_weight;
    int[] fin;

    public Weight(int[] nums, int[] nums_weight){
        this.nums = nums;
        this.nums_weight = nums_weight;
    }

    public Weight(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество чисел: ");
        int coll = in.nextInt();
        Random random = new Random();
        int[] nums = new int[coll];
        int[] nums_weight = new int[coll];

        for (int i = 0; i < coll; i++){
            int num = random.nextInt(400);
            nums[i] = num;
            int num_weight = random.nextInt(10);
            nums_weight[i] = num_weight;
        }
        this.nums = nums;
        this.nums_weight = nums_weight;
    }

    public int[] Final(Weight obj){
        int[] final_int = new int[] {};
        for (int i = 0; i < obj.nums.length; i++){
            for (int j = 0; j < obj.nums_weight[i]; j++){
                final_int = Arrays.copyOf(final_int, final_int.length + 1);
                final_int[final_int.length-1] = obj.nums[i];
            }
        }
        obj.fin = final_int;
        return final_int;
    }

    public String Rand(Weight obj, int num_rep){
        String fin = new String();
        Random random = new Random();
        for (int t=0; t<num_rep; t++){
            int rand = random.nextInt(obj.fin.length);
            fin += "|" + obj.fin[rand] + "|";
        }
        return fin;
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 4, 8};
        int[] b = new int[] {1, 2, 5};

        Weight new_wl = new Weight(a, b);
        int[] out = new_wl.Final(new_wl);

        for (int g = 0; g < out.length; g++){
            System.out.print("|" + out[g] + "|");
        }
        System.out.println();
        System.out.println("Вывод функции RAND -> ");
        System.out.println(new_wl.Rand(new_wl, 10));

    }
}
