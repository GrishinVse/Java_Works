package com.spring_work.grishin.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question extends AuditModel {

    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "score")
    private Integer score;

    @Column(name = "active")
    private Boolean active;

    // Список ответов
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    List<Answer> answerList;

    // Тест
    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    Test test;

    // Конструкторы
    public Question(){}

    public Question(String text, Integer score, Boolean active) {
        this.text = text;
        this.score = score;
        this.active = active;
        answerList = new ArrayList<>();
    }

    // ID вопроса
    public Long getId() {
        return id;
    }

    // Текст вопроса
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Баллы вопроса
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    // Активность вопроса
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // Список ответов
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswer(Answer answer){
        answerList.add(answer);
        answer.setQuestion(this);
    }

    // Тест
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", score=" + score +
                ", active=" + active +
                ", answerList=" + answerList +
                ", test=" + test +
                '}';
    }
}