package com.interfaces;

public class Book implements Printable {
    private String name;
    private String author;

    public Book(String name, String author){
        this.name = name;
        this.author = author;
    }

    @Override
    public void print() {
        System.out.printf("%s - %s" , name, author);
    }
}
