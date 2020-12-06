package com.spring_work.grishin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    private final String domain_name = "edu.fa.ru";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String f_name;

    @Column(name = "last_name")
    private String l_name;

    @Column(name = "middle_name")
    private String m_name;

    @Column(name = "login")
    private String login;

    @Column(name = "password_hash")
    private String password;

    /* Зависимые атрибуты */

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TestList> test_List;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Student_answer> student_answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", updatable = false, insertable = false)
    @JsonIgnore
    private Group group;
    @Column(name = "group_id")
    private Long group_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private Role role;
    @Column(name = "role_id")
    private Long role_id;

    // Конструкторы
    public User(){ }

    public User(String first_name, String last_name, String middle_name, String login, String password_hash) {
        this.f_name = first_name;
        this.l_name = last_name;
        this.m_name = middle_name;
        this.login = login;
        this.password = password_hash;
        test_List = new ArrayList<>();
        student_answerList = new ArrayList<>();
    }

    // ID пользователя
    public long getId() {
        return id;
    }

    // Полное имя пользователя
    public String getFull_name() {
        return l_name + ' ' + f_name + ' ' + m_name;
    }

    public void setFull_name(String f_name, String l_name, String m_name) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.m_name = m_name;
    }

    // Логин
    private String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // Пароль
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email = getLogin() + "@" + domain_name;

    public String getEmail() {
        return email;
    }

    /* Зависимые атрибуты */

    public List<TestList> getTest_List() {
        return test_List;
    }

    public void setTest_List(List<TestList> test_List) {
        this.test_List = test_List;
    }

    public List<Student_answer> getStudent_answerList() {
        return student_answerList;
    }

    public void setStudent_answerList(List<Student_answer> student_answerList) {
        this.student_answerList = student_answerList;
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

    // Роль и её ID
    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(Role role, Long role_id) {
        this.role = role;
        setRole_id(role_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "domain_name='" + domain_name + '\'' +
                ", id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", m_name='" + m_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", test_List=" + test_List +
                ", student_answerList=" + student_answerList +
                ", group=" + group +
                ", group_id=" + group_id +
                ", role=" + role +
                ", role_id=" + role_id +
                ", email='" + email + '\'' +
                '}';
    }
}
