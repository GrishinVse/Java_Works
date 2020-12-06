package com.spring_work.grishin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_list")
public class TestList {
    @Id
    @Column(name = "row_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private Subject subject;
    @Column(name = "subject_id")
    private Long subject_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private User teacher;
    @Column(name = "teacher_id")
    private Long teacher_id;

    @OneToOne(mappedBy = "testList", cascade = CascadeType.ALL)
    Schedule schedule;

    @OneToMany(mappedBy = "testList", cascade = CascadeType.ALL)
    List<Test> testList;

    @OneToMany(mappedBy = "testList", cascade = CascadeType.ALL)
    private List<Student_answer> student_answerList;

    // Конструкторы
    public TestList(){ }

    public TestList(String name) {
        this.name = name;
        testList = new ArrayList<>();
        student_answerList = new ArrayList<>();
    }

    // ID списка тестов
    public Long getId() {
        return id;
    }

    // Название
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ID предмета (дисциплины)
    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    // Предмет (дисциплина)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSubject(Subject subject, Long subject_id) {
        this.subject = subject;
        setSubject_id(subject_id);
    }

    // ID преподавтеля
    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    // Преподаватель
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setTeacher(User teacher, Long teacher_id) {
        this.teacher = teacher;
        setTeacher_id(teacher_id);
    }

    // Рассписание
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
        schedule.setTestList(this);
    }

    // Список тестов
    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public void addTest(Test test) {
        testList.add(test);
        test.setTestList(this);
    }

    // Список ответов студентов
    public List<Student_answer> getStudent_answerList() {
        return student_answerList;
    }

    public void setStudent_answerList(List<Student_answer> student_answerList) {
        this.student_answerList = student_answerList;
    }

    @Override
    public String toString() {
        return "TestList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", subject_id=" + subject_id +
                ", teacher=" + teacher +
                ", teacher_id=" + teacher_id +
                ", schedule=" + schedule +
                ", testList=" + testList +
                ", student_answerList=" + student_answerList +
                '}';
    }
}
