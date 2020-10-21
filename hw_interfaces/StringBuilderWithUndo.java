package com.hw_interfaces;
import java.util.Stack;

/* Реализовать класс обертку StringBuilder с поддержкой undo.
   Делегировать стандартные методы стандартному StringBuilder. Паттерн «Команда» */

public class StringBuilderWithUndo {

    private class DeleteAction implements IntAction{
        private int size;

        public DeleteAction(int size){
            this.size = size;
        }

        @Override
        public void undo(){
            stringBuilder.delete(stringBuilder.length() - size, stringBuilder.length());
        }
    }

    private StringBuilder stringBuilder;

    // Контейнер для хранения истории взаимодействия
    private Stack<IntAction> undoStack;

    // Конструктор
    public StringBuilderWithUndo(){
        this.stringBuilder = new StringBuilder();
    }

    public StringBuilderWithUndo(String str){
        this.stringBuilder = new StringBuilder(str);
    }

    // Главные методы
    // Добавление в конец строки
    public StringBuilderWithUndo append(String str){
        stringBuilder.append(str);
        undoStack.add(() -> stringBuilder.delete(stringBuilder.length() - str.length() /* -1 */, stringBuilder.length()));
        return this;
    }

    // Добавление строки по определенному индексу
    public StringBuilderWithUndo insert(int index, String str){
        stringBuilder.insert(index, str);
        undoStack.add(() -> stringBuilder.delete(index, str.length()));
        return this;
    }
    // Добавление символа по определенному индексу
    public StringBuilderWithUndo insert(int index, Character ch){
        stringBuilder.insert(index, ch);
        undoStack.add(() -> stringBuilder.delete(index, 1));
        return this;
    }

    // Обрезка строки с определелнного символа до конца
    public StringBuilderWithUndo substring(int index){
        String del = stringBuilder.substring(0, index);
        stringBuilder.substring(index, stringBuilder.length());
        undoStack.add(() -> stringBuilder.insert(index, del));
        return this;
    }
    // Обрезка строки в определнном интевале
    public StringBuilderWithUndo substring(int start, int end){
        String del = stringBuilder.substring(0, stringBuilder.length()); // сохраняем всю строку
        stringBuilder.substring(start, end);
        undoStack.add(() -> stringBuilder.replace(0, stringBuilder.length(), del));
        return this;
    }

    // Удаление элементов на промежутке
    public StringBuilderWithUndo delete(int begin, int len){
        String del = stringBuilder.substring(begin, len);
        stringBuilder.delete(begin, len);
        undoStack.add(() -> stringBuilder.insert(begin, del));
        return this;
    }
    // Удаление элемента по индексу
    public StringBuilderWithUndo deleteCharAt(int index){
        char del = stringBuilder.charAt(index);
        stringBuilder.deleteCharAt(index);
        undoStack.add(() -> stringBuilder.insert(index, del));
        return this;
    }

    // Смена порядка на обратный
    public StringBuilderWithUndo reverse() {
        stringBuilder.reverse();
        undoStack.add(() -> stringBuilder.reverse());
        return this;
    }

    // Замена на другую подстроку
    public StringBuilderWithUndo replace(int start, int end, String str){
        String del = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);
        undoStack.add(() -> stringBuilder.replace(start, end, del));
        return this;
    }

    public void undo(){
        if(!undoStack.isEmpty()){
            undoStack.pop().undo();
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }
}