package com.company;
import java.util.Scanner;


public class Main
{

    public static int returnFirst(int x, int y) {
        return x;
    }

    public static void main(String[] args) {
        /* Первая задача */
        System.out.println("\nОтвет на задачу №1\nHello World");
        /* Вторая задача */
        System.out.println(Main.retA());
        /* Третья задача */
        System.out.println("\nОтвет на задачу №3");
        int result[] = retB();
        for(int i=0; i<result.length; i=i+1) {
            System.out.println("b + 1 = " + result[i]);
        }
        /* Четвертая задача */
        System.out.println(Main.Four());
        /* Пятая задача */
        System.out.println(Main.Five());
        /* Шестая задача */
        System.out.println(Main.Six());
        /* Седьмая задача */
        System.out.println(Main.Seven());
        /* Восьмая задача */
        System.out.println(Main.Eight());
        /* Девятая задача */
        System.out.println(Main.Nine());
        /* Десятая задача */
        System.out.println(Main.Ten());
        /* Одиннадцатая задача */
        System.out.println(Main.Eleven());
        /* Двенадцатая задача */
        System.out.println(Main.Twelve());
        // System.out.println("d = " + result[1]);

    }
    // Создайте переменную, присвойте ей целочисленное значение. Выведите значение на экран
    public static String retA() {
	    int a = 5;
        return "\nОтвет на задачу №2\n" + "a = " + a;
    }

    // Создайте переменную, увеличьте её на единицу несколькими способами и выведите значение на экран.
    public static int[] retB() {
         int b = 10;
         int b1 = ++b;
         int b2 = b++;
         int b3 = b+1;
         return new int[] {b1, b2, b3};
    }

    // Даны две переменные. Поменяйте значения переменных друг с другом двумя способами.
    public static String Four() {
        //Смена значений двух переменных Вар №1
        String fin1 = "\nОтвет на задачу №4\nСмена значений двух переменных Вар №1\n";
        int a = 17;
        int b = 3;
        fin1 += "a = " + a + " ";
        fin1 += "b = " + b + "\n";
        int CHANGER = a;
        a = b;
        b = CHANGER;
        fin1 += "NEW a = " + a + " ";
        fin1 += "NEW b = " + b + "\n";
        //Смена значений двух переменных Вар №2
        String fin2 = "Смена значений двух переменных Вар №2\n";
        int c = 20;
        int d = 15;
        fin2 += "c = " + c + " ";
        fin2 += "d = " + d + "\n";
        c = Main.returnFirst(d, d = c);
        fin2 += "NEW c = " + c + " ";
        fin2 += "NEW d = " + d + "\n";
        return fin1 + fin2;
    }

    // Дано два числа a и b. Найдите гипотенузу треугольника с заданными катетами.
    public static String Five() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №5\nВведите первый катет: ");
        double kat1 = in.nextDouble();
        System.out.print("Введите второе катет: ");
        double kat2 = in.nextDouble();
        double res = Math.sqrt(kat1*kat1 + kat2*kat2);
        return "Гипотенуза = " + res;
    }

    // Дано натуральное число. Выведите его последнюю цифру.
    public static String Six() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №6\nВведите число: ");
        int a = in.nextInt();
        return "Последняя цифра числа " + a + " = " + a % 10;
    }

    // Дано неотрицательное целое число. Найдите число десятков в его десятичной записи (то есть вторую справа цифру его десятичной записи).
    public static String Seven() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №7\nВведите число: ");
        int a = in.nextInt();
        int b = (a % 100) / 10;
        return "Вторая с конца цифра числа " + a + " = " + b;
    }

    // Дано двузначное число. Найдите число десятков в нем.
    public static String Eight() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №8\nВведите число: ");
        int a = in.nextInt();
        int b = a / 10;
        return "Число десятков " + a + " = " + b;
    }

    // Реализуйте метод, который получает целое число на вход и возвращает разницу между данным числом и 21.
    // Выведите значение на экран с различными целыми числами.
    public static String Nine() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №9\nВведите количество чисел, которые вы собирайтесь ввести: ");
        int number = in.nextInt();
        String res = "Отчет --> ";
        for(int i=0; i<number; i=i+1) {
            System.out.println("Введите число: ");
            int a = in.nextInt();
            String str = Integer.toString(a);
            res += str + " - 21 = " + (a - 21) + " | ";
        }
        return res;
    }

    // Реализуйте метод, в который передается две целочисленные переменные и возвращает их среднее арифметическое
    public static String Ten() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №10\nВведите количество чисел, которые вы собирайтесь ввести: ");
        int number = in.nextInt();
        int sum = 0;
        for(int i=0; i<number; i=i+1) {
            System.out.println("Введите число: ");
            int a = in.nextInt();
            sum += a;
        }
        double res = sum/number;
        return "Среднее ариф-ое = " + res;
    }

    // Реализуйте метод, в который передается две целочисленные переменные и возвращает их среднее геометрическое
    public static String Eleven() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №11\nВведите число 1: ");
        int a = in.nextInt();
        System.out.print("\nВведите число 2: ");
        int b = in.nextInt();
        double res = Math.sqrt(a*b);
        return "Среднее геом-ое = " + res;
    }

    // Реализуйте метод, в который передается 4 числа с плавающей точкой.
    // Первые два числа – координаты x, y первой точки.
    // Вторые два числа – координаты x,y второй точки.
    // Найти расстояние между двумя точками.
    public static String Twelve() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nОтвет на задачу №12\nВведите X1: ");
        double x1 = in.nextDouble();
        System.out.print("\nВведите Y1: ");
        double y1 = in.nextDouble();
        System.out.print("\nВведите X2: ");
        double x2 = in.nextDouble();
        System.out.print("\nВведите Y2: ");
        double y2 = in.nextDouble();
        // AB = √((x2 - x1)^2 + (y2 - y1)^2)
        double r = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
        double res = Math.sqrt(r);
        return "Расстояние = " + res;
    }
}