package com.example.springnewui.models;

import com.example.springnewui.utils.DateUtils;
import com.google.gson.Gson;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Person{
    private final LongProperty id; // Связь с данными человека
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final IntegerProperty postalCode;
    private final ObjectProperty<LocalDate> birthday;

    public Person(){
        this(Long.valueOf(1), "Иван", "Иванов", "Улица Ленина", "Москва", 111111, LocalDate.of(1900, 1, 1));
    }

    /*
    public Person(){
        this(Long.valueOf(1), null, null, null, null, 0, null);
    }
     */


    public Person(String firstName, String lastName, String street, String city, Integer postalCode, LocalDate birthday){
        this.id = null;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        this.street = new SimpleStringProperty(street); //"Улица Ленина"
        this.city = new SimpleStringProperty(city); //"Москва"
        this.postalCode = new SimpleIntegerProperty(postalCode); //123456
        this.birthday = new SimpleObjectProperty<LocalDate>(birthday); //LocalDate.of(1990, 1, 1)
    }

    public Person(Long id, String firstName, String lastName, String street, String city, Integer postalCode, LocalDate birthday){
        this.id = new SimpleLongProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        this.street = new SimpleStringProperty(street); //"Улица Ленина"
        this.city = new SimpleStringProperty(city); //"Москва"
        this.postalCode = new SimpleIntegerProperty(postalCode); //123456
        this.birthday = new SimpleObjectProperty<LocalDate>(birthday); //LocalDate.of(1990, 1, 1)
    }

    // Основные гетеры

    public long getId() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getCity() {
        return city.get();
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    // Сетеры

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public void setId(Long id) {this.id.set(id);}

    // Гетеры для связи

    public LongProperty idProperty() { return id; } // getIdProperty

    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public StringProperty getStreetProperty() {
        return street;
    }

    public StringProperty getCityProperty() {
        return city;
    }

    public IntegerProperty getPostalCodeProperty() {
        return postalCode;
    }

    public ObjectProperty<LocalDate> getBirthdayProperty() {
        return birthday;
    }

    /*
    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", firstName.getValue());
        map.put("lastName", lastName.getValue());
        map.put("city", city.getValue());
        map.put("postalCode", String.valueOf(postalCode.getValue()));
        map.put("birthday", DateUtils.format(birthday.getValue()));

        Gson gson = new Gson();

        return gson.toJson(map);
    }

     */

    @Override
    public String toString() {
        return "{" +
                "id : " + getId() + ",\n" +
                "firstName : " + getFirstName() + ",\n" +
                "lastName : " + getLastName() + ",\n" +
                "street : " + getStreet() + ",\n" +
                "city : " + getCity() + ",\n" +
                "postalCode : " + getPostalCode() + ",\n" +
                "birthday : " + getBirthday() + ",\n" +
                '}';
    }
}
