package com.company;

import java.util.Arrays;

public class Vector {
    public double x;
    public double y;
    public double z;
    private Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double lenVector() {
        return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
    }

    public double scalar(Vector obj){
        return this.x*obj.getX() + this.y * obj.getY() + this.z * obj.getZ();
    }

    public double[] mulVector(Vector obj) {
        double [] arr = new double[3];
        arr[0] = this.y * obj.getZ() - this.z * obj.getY();
        arr[1] = this.z * obj.getX() - this.x * obj.getZ();
        arr[2] = this.x * obj.getY() - this.y * obj.getX();
        return arr;
    }

    public double angle(Vector obj) {
        return this.scalar(obj) / (this.lenVector()*obj.lenVector());
    }

    public double[] sum(Vector obj){
        double [] arr = new double[3];
        arr[0] = this.x + obj.x;
        arr[1] = this.y + obj.y;
        arr[2] = this.z + obj.z;
        return arr;
    }

    public double[] sub(Vector obj){
        double [] arr = new double[3];
        arr[0] = this.x - obj.x;
        arr[1] = this.y - obj.y;
        arr[2] = this.z - obj.z;
        return arr;
    }

    public static double[][] someVectors(int n) {
        double [][] arr = new double[n][3];
        for (int i=0; i < n; i++) {
            arr[i][0] = Math.random()*(200+1) - 100;
            arr[i][1] = Math.random()*(200+1) - 100;
            arr[i][2] = Math.random()*(200+1) - 100;
        }
        return arr;
    }

    // Вывод через консоль
    public String print() {
        String outline = "";
        outline += "Координаты: " + Double.toString(this.x) +" | "+Double.toString(this.y) + " | "+Double.toString(this.z);
        return outline;
    }

    public static void main(String[] args) {
        System.out.println("Вектор а");
        Vector a = new Vector(1,2,3);
        System.out.println(a.print());
        System.out.println(a.lenVector());

        System.out.println("Вектор b");
        Vector b = new Vector(3,2,1);
        System.out.println(b.print());
        System.out.println(a.scalar(b));
//        System.out.println(Arrays.toString(a.mulVector(b)));
//        System.out.println(a.angle(b));
//        System.out.println(Arrays.toString(a.sum(b)));
//        System.out.println(Arrays.toString(a.sub(b)));
        for (int i=0; i < a.someVectors(5).length; i++) {
            System.out.println(Arrays.toString(a.someVectors(5)[i]));
        }
    }
}
