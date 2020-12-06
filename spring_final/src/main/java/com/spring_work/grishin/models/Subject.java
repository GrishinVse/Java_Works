package com.spring_work.grishin.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<TestList> testList_;

    // Конструкторы
    public Subject(){ }

    public Subject(String name) {
        this.name = name;
        testList_ = new ArrayList<>();
    }

    // ID
    public long getId() {
        return id;
    }

    // Название
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Список тестов
    public List<TestList> getTestList() {
        return testList_;
    }

    public void setTestList(List<TestList> testList) {
        this.testList_ = testList;
    }

    public void addTestList(TestList testList) {
        testList_.add(testList);
        testList.setSubject(this);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testList_=" + testList_ +
                '}';
    }
}
