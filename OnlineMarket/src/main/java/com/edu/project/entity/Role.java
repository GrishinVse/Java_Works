package com.edu.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Connection with User
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role_id")
    private Set<Client> clients;

    public Role(){}

    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
