package models;

import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "row_id")
    private int id;

    @OneToMany(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private TestList testList;

    @OneToMany(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "duration")
    private int duration;

    @Column(name = "start_dt")
    private Date start_dt;

    @Column(name = "end_dt")
    private Date end_dt;

    @Column(name = "start_time")
    private Time start_time;

    @Column(name = "end_time")
    private Time end_time;

    @Column(name = "active")
    private Boolean active;

    public Schedule(){ }
}
