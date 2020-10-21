package com.hw_interfaces;

/* Написать класс StringBuilder с оповещением других объектов-слушателей об изменении своего состояния.
   Делегировать стандартные методы стандартному StringBuilder. Паттерн «Наблюдатель» */

public class StringBuilderWithWatcher {
    // Текущий наблюдатель
    private IntWatcher CurWatcher;
    public void setCurWatcher(IntWatcher curWatcher) {
        this.CurWatcher = curWatcher;
    }

    private StringBuilder stringBuilder;

    // Конструктор
    public StringBuilderWithWatcher(){
        this.stringBuilder = new StringBuilder();
    }

    public StringBuilderWithWatcher(String str){
        this.stringBuilder = new StringBuilder(str);
    }

    private void notifyCurWatcher(){
        if (CurWatcher != null){
            CurWatcher.onChange(this);
        }
    }

    // Главные методы
    // Добавление в конец строки
    public StringBuilderWithWatcher append(String str){
        stringBuilder.append(str);
        notifyCurWatcher();
        return this;
    }

    // Добавление строки по определенному индексу
    public StringBuilderWithWatcher insert(int index, String str){
        stringBuilder.insert(index, str);
        notifyCurWatcher();
        return this;
    }
    // Добавление символа по определенному индексу
    public StringBuilderWithWatcher insert(int index, Character ch){
        stringBuilder.insert(index, ch);
        notifyCurWatcher();
        return this;
    }

    // Обрезка строки с определелнного символа до конца
    public StringBuilderWithWatcher substring(int index){
        stringBuilder.substring(index, stringBuilder.length());
        notifyCurWatcher();
        return this;
    }
    // Обрезка строки в определнном интевале
    public StringBuilderWithWatcher substring(int start, int end){
        stringBuilder.substring(start, end);
        notifyCurWatcher();
        return this;
    }

    // Удаление элементов на промежутке
    public StringBuilderWithWatcher delete(int begin, int end){
        stringBuilder.delete(begin, end);
        notifyCurWatcher();
        return this;
    }
    // Удаление элемента по индексу
    public StringBuilderWithWatcher deleteCharAt(int index){
        stringBuilder.deleteCharAt(index);
        notifyCurWatcher();
        return this;
    }

    // Смена порядка на обратный
    public StringBuilderWithWatcher reverse() {
        stringBuilder.reverse();
        notifyCurWatcher();
        return this;
    }

    // Замена на другую подстроку
    public StringBuilderWithWatcher replace(int start, int end, String str){
        stringBuilder.replace(start, end, str);
        notifyCurWatcher();
        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public static void main(String[] strings) {
        StringBuilderWithWatcher someStringBuilder = new StringBuilderWithWatcher();
        Watcher MyWatcher = new Watcher();
        someStringBuilder.setCurWatcher(MyWatcher);
        someStringBuilder.append("adasda");
        someStringBuilder.append(" < - ");
        someStringBuilder.delete(4, 6);
    }
}
