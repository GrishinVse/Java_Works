package models;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @Column(name = "row_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "subject_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private TestList testList;

    public Subject(){ }

}
