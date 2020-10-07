package com.hw_8_10;

//Дан итератор. Метод next() возвращает либо String
// либо итератор такой же структуры(то есть либо опять String либо опять итератор).

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class String_or_iter<T> implements Iterator<T> {
    private Iterator<T> final_iter;
    private boolean index = false;
    private int ItOrSt = 0;
    private String final_str = " | ";

    public String_or_iter(Iterator<T> input_iter){
        this.final_iter = input_iter;
        this.index = input_iter.hasNext();
    }

    @Override
    public boolean hasNext() {
        return index != false;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        if (ItOrSt % 2 == 0){
            ItOrSt++;
            final_str += Integer.toString((Integer) final_iter.next()) + " | ";
            return (T) final_str;
        } else if (ItOrSt % 2 == 1){
            ItOrSt++;
            return (T) new String_or_iter(final_iter);
        }
        return null;
    }

    public static void main(String[] args) {
        Iterator<Integer> iter1 = Arrays.asList(4, 2, 0, 4, 6, 4, 9).iterator();
        String_or_iter new_iter = new String_or_iter(iter1);
        for (int i = 0; i<14;i++){
            System.out.println(new_iter.next());
        }
    }
}
