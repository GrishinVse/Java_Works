package com.spring_work.grishin.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<User> userList;

    // Конструкторы
    public Role(){ }

    public Role(String name) {
        this.name = name;
        userList = new ArrayList<>();
    }

    // ID роли
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

    // Список пользователей в группе
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
        user.setRole(this);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
