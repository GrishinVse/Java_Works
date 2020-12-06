package com.spring_work.grishin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer extends AuditModel {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "correct")
    private Boolean correct;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false, updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Question question;

    @Column(name = "question_id")
    Long question_id;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Student_answer> student_answerList;

    // Конструкторы
    public Answer(){ }

    public Answer(String text, Boolean correct){
        this.text = text;
        this.correct = correct;
        student_answerList = new ArrayList<>();
    }

    // ID ответа
    public Long getId() {
        return id;
    }

    // Ответ
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Правильность ответа
    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
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

    // Список ответов студента
    public List<Student_answer> getStudent_answerList() {
        return student_answerList;
    }

    public void setStudent_answerList(List<Student_answer> student_answerList) {
        this.student_answerList = student_answerList;
    }

    public void addStudent_answer(Student_answer student_answer){
        student_answerList.add(student_answer);
        student_answer.setAnswer(this);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                ", question=" + question +
                ", question_id=" + question_id +
                ", student_answerList=" + student_answerList +
                '}';
    }
}
