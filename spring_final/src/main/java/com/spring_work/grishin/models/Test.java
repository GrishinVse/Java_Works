package com.spring_work.grishin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    TestList testList;
    @Column(name = "test_id")
    private Long test_id;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    Question question;
    @Column(name = "question_id")
    private Long question_id;

    // Конструкторы
    public Test(){ }

    public Test(Question question) {
        this.question = question;
        question.setTest(this);
    }

    // ID
    public Long getId() {
        return id;
    }

    // ID теста
    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    // Список тестов
    public TestList getTestList() {
        return testList;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

    public void setTestList(TestList testList, Long test_id) {
        this.testList = testList;
        setTest_id(test_id);
    }

    // ID вопроса
    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    // Вопрос
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setQuestion(Question question, Long question_id) {
        this.question = question;
        setQuestion_id(question_id);
    }
}
