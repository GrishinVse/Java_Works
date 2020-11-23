package models;

import javax.persistence.*;

@Entity
@Table(name = "student_answer")
public class Student_answer {
    @Id
    @Column(name = "row_id")
    private int id;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private TestList testList;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Student_answer(){ }
}
