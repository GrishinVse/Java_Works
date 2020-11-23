package models;

import javax.persistence.*;

@Entity
@Table(name = "test_list")
public class TestList {
    @Id
    @Column(name = "row_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_id")
    private User user;

    public TestList(){ }
}
