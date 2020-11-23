package models;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @Column(name = "row_id")
    private int id;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "text")
    private String text;

    @Column(name = "correct")
    private Boolean correct;

    public Answer(){ }
}
