package com.company;

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
        return (index < twoDimArray.length) & (jndex < twoDimArray[0].length);
    }

    @Override
    public T next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        if (jndex == twoDimArray[index].length - 1){
            jndex = 0;
            index = index + 1;
        } else {
            jndex = jndex + 1;
        }
        return twoDimArray[index][jndex];
    }
}
