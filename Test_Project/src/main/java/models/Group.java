package models;

import javax.persistence.*;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @Column(name = "row_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "semester")
    private int semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Group(){ }
}
