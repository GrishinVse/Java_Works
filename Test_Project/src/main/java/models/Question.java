package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @Column(name = "row_id")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "score")
    private int score;

    @Column(name = "active")
    private Boolean active;

    public Question(){ }

}
