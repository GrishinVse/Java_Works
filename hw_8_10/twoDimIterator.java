package com.hw_8_10;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class twoDimIterator<T> implements Iterator<T> {
    private T[][] twoDimArray;
    private int index = 0;
    private int jndex = 0;

    public twoDimIterator(T[][] twoDimArray){
        this.twoDimArray = twoDimArray;
    }

    @Override
    public boolean hasNext() {
        // Проверка на то, что есть следующий элемент
        if (jndex >= twoDimArray[index].length){
            jndex = 0;
            index = index+1;
        }
        return (index <= twoDimArray.length) & (jndex <= twoDimArray[index].length);
    }

    @Override
    public T next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        return twoDimArray[index][jndex++];
    }

    public static void main(String[] args) {
        Integer[][] test_ar = {{2, 5, 7}, {1, 4, 9, 13, 11, 6}};

        twoDimIterator TwoIter = new twoDimIterator(test_ar);
        for (int i = 0; i < test_ar.length; i++){
            for (int j =0; j < test_ar[i].length; j++){
                System.out.println("--> " + TwoIter.next());
            }
            System.out.println("---TAB---");
        }
    }
}