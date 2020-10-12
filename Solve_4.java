package com.hw_8_10;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Solve_4 implements Iterator<String> {
    private Stack<Iterator> iterators;
    private String next;
    private boolean hasNext;

    public Solve_4(Iterator<?> iterator){
        this.iterators = new Stack<Iterator>();
        this.iterators.push(iterator);

        updateNext();
    }

    private void updateNext(){
        if (iterators.empty()){
            next = null;
            hasNext = false;
            return;
        }

        Iterator current = iterators.peek();

        if (current.hasNext()){
            Object o = current.next();
            if (o instanceof String){
                next = (String) o;
                hasNext = true;
            } else if (o instanceof Iterator){
                Iterator iterator = (Iterator) o;
                iterators.push(iterator);
                updateNext();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            iterators.pop();
            updateNext();
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public String next() {
        if (!hasNext){
            throw new NoSuchElementException();}

        String nextToReturn = next;
        updateNext();
        return nextToReturn;
    }

    @Override
    public void remove(){
        throw new 
    }
}
