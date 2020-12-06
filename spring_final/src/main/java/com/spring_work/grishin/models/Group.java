package com.spring_work.grishin.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "semester")
    private int semester;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> userList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList;

    // Конструкторы
    public Group(){ }

    public Group(String name, Integer year, Integer semester) {
        this.name = name;
        this.year = year;
        this.semester = semester;
        userList = new ArrayList<>();
        scheduleList = new ArrayList<>();
    }

    // ID группы пользователей
    public long getId() {
        return id;
    }

    // Название
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Год
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Семестр
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    // Список пользователей
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
        user.setGroup(this);
    }

    // Список расписания
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    private void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
        schedule.setGroup(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                ", userList=" + userList +
                ", scheduleList=" + scheduleList +
                '}';
    }
}
