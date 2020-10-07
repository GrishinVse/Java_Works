package com.hw_8_10;

import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorForIterators<T> implements Iterator<T> {
    private Iterator<Iterator<T>> iters;
    private Iterator<T> cur_iter;

    public IteratorForIterators(List<Iterator<T>> iters) {
        this.iters = iters.iterator();
    }

    @Override
    public boolean hasNext() {
        cur_iter = iters.next();
        return cur_iter != null;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        String out_line = new String();

        for (Iterator<T> it = cur_iter; it.hasNext(); ) {
            while (it.hasNext()){
                out_line += " | " + Integer.toString((Integer) it.next());
            }
        }
        return (T) out_line;
    }

    public static void main(String[] args) {
        Iterator<Integer> i1 = Arrays.asList(4, 2, 0, 4, 6, 4, 9).iterator();
        Iterator<Integer> i2 = Arrays.asList(0, 9, 8, 7, 5).iterator();
        Iterator<Integer> i3 = Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4).iterator();

        List<Iterator<Integer>> main_list = Arrays.asList(i1, i2, i3);

        IteratorForIterators main_iter = new IteratorForIterators(main_list);
        System.out.println(main_iter.next());
        System.out.println(main_iter.next());
        System.out.println(main_iter.next());
        System.out.println(main_iter.next());
    }
}
