package com.company;

import java.util.Scanner;

class Matrix {
    int matrix_len;
    int matrix_wid;
    int[][] matrix;

    public Matrix() {
        Scanner in = new Scanner(System.in);
        // matrixA = new int[2][3];
        // Матрица A имеет размерность 2 на 3 (2 строки, 3 столбца).
        System.out.println("Введите количество строк в матрице: ");
        matrix_wid = in.nextInt();
        System.out.println("Введите количество столбцов в матрице: ");
        matrix_len = in.nextInt();
        matrix = new int[matrix_wid][matrix_len];
    }

    public Matrix(int a, int b) {
        matrix_wid = a;
        matrix_len = b;
        matrix = new int[matrix_wid][matrix_len];
    }

    public Matrix(int size) {
        matrix_wid = matrix_len = size;
        matrix = new int[matrix_wid][matrix_len];
    }

    // Создание матрицы
    public void CreateMatrix() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < matrix_wid; i++) {
            for (int j = 0; j < matrix_len; j++) {
                System.out.print("-> ");
                int num = in.nextInt();
                matrix[i][j] = num;
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Вывод через консоль
    public void Print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("|" + matrix[i][j] + "|");
            }
            System.out.println();
        }
    }

    public final boolean checkSquare() {
        //           rows == columns
        return matrix_wid == matrix_len;
    }

    // Сложение матриц (вычитание?)
    public Matrix Sum(Matrix matrix1, Matrix matrix2) {
        int size;

        if (!matrix1.checkSquare()) {
            size = matrix1.matrix_wid > matrix1.matrix_len ? matrix1.matrix_len : matrix1.matrix_wid;
        } else {
            size = matrix1.matrix_wid;
        }

        Matrix matrix = new Matrix(size);

        for (int i = 0; i < matrix1.matrix_wid; i++) {
            for (int j = 0; j < matrix1.matrix_len; j++) {
                matrix.matrix[i][j] = matrix1.matrix[i][j] + matrix2.matrix[i][j];
            }
        }

        return matrix;
    }

    // Умножение двух
    public Matrix Mul(Matrix matrix1, Matrix matrix2){
        int size;
        int n;

        if (!matrix1.checkSquare()) {
            size = matrix1.matrix_wid > matrix1.matrix_len ? matrix1.matrix_len : matrix1.matrix_wid;
            n = matrix1.matrix_wid > matrix1.matrix_len ? matrix1.matrix_wid : matrix1.matrix_len;
        } else {
            size = matrix1.matrix_wid;
            n = matrix1.matrix_wid;
        }

        Matrix matrix = new Matrix(size);
        matrix.fill(0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < n; k++) {
                    matrix.matrix[i][j] += matrix1.matrix[i][k] * matrix2.matrix[k][j];
                }
            }
        }

        return matrix;
    }

    private void fill(int n) {
        for(int i = 0; i < matrix_wid; i++) {
            for(int j = 0; j < matrix_len; j++) {
                matrix[i][j] = n;
            }
        }
    }


    public void setX(int x) {
        matrix_wid = x;
    }

    public int getX() {
        System.out.println("Кол-во строк:" + matrix_wid);
        return matrix_wid;
    }

    public void setY(int y) {
        matrix_len = y;
    }

    public int getY() {
        System.out.println("Кол-во столбцов:" + matrix_len);
        return matrix_len;
    }

    public static void main(String[] args) {
        System.out.println("Это телевидение?!");
        Matrix M = new Matrix();
        M.CreateMatrix();
        M.Print();
    }
}