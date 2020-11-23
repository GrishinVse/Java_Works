package models;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "row_id")
    private int id;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private TestList testList;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private Question question;

    public Test(){ }
}
