package models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private final String domain_name = "edu.fa.ru";

    @Id
    @Column(name = "row_id")
    private int id;

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

    @OneToMany(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne(mappedBy = "row_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "teacher_id", cascade = CascadeType.ALL)
    private TestList testList;

    public User(){ }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return l_name + ' ' + f_name + ' ' + m_name;
    }

    private String getLogin() {
        return login;
    }

    private String email = getLogin() + "@" + domain_name;

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getFull_name() + '\'' +
                ", email=" + getEmail() +
                '}';
    }
}
