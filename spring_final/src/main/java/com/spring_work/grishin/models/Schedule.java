package com.spring_work.grishin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    Group group;
    @Column(name = "group_id")
    private Long group_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    TestList testList;
    @Column(name = "test_id")
    private Long test_id;

    // Конструкторы
    public Schedule(){ }

    public Schedule(Integer duration, Date start_dt, Time start_time, Date end_dt, Time end_time, Boolean active) {
        this.duration = duration;
        this.start_dt = start_dt;
        this.start_time = start_time;
        this.end_dt = end_dt;
        this.end_time = end_time;
        this.active = active;
        TestList testList;
    }

    // ID рассписания
    public long getId() {
        return id;
    }

    // Длительность
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Дата начала
    public Date getStart_dt() {
        return start_dt;
    }

    public void setStart_dt(Date start_dt) {
        this.start_dt = start_dt;
    }

    // Время начала
    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    // Дата конца
    public Date getEnd_dt() {
        return end_dt;
    }

    public void setEnd_dt(Date end_dt) {
        this.end_dt = end_dt;
    }

    // Время конца
    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    // Состояние
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // Группа и её ID
    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setGroup(Group group, Long group_id) {
        this.group = group;
        setGroup_id(group_id);
    }

    // Список тестов и ID
    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public TestList getTestList() {
        return testList;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

    public void setTestList(TestList testList, Long test_id) {
        this.testList = testList;
        setTest_id(test_id);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", duration=" + duration +
                ", start_dt=" + start_dt +
                ", end_dt=" + end_dt +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", active=" + active +
                ", group=" + group +
                ", group_id=" + group_id +
                ", testList=" + testList +
                ", test_id=" + test_id +
                '}';
    }
}
