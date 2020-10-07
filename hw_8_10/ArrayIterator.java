package com.hw_8_10;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int index = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        return array[index++];
    }

    public static void main(String[] args) {
        Integer[] test_ar = {2, 5, 7, 8, 3, 0};
        String[] test_ar2 = {"sdnksan", "mknsi", "sjdnd", "djsbs"};

        ArrayIterator NewArrayIter = new ArrayIterator(test_ar);
        //ArrayIterator NewArrayIter = new ArrayIterator(test_ar2);
        for (int i = 0; i < test_ar.length; i++){
            System.out.println("--> " + NewArrayIter.next());
        }
    }
}