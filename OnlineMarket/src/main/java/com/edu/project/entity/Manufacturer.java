package com.edu.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Manufacturer implements Serializable {
    @Id
    @Column(name = "id")
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

    // Connection with Model
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand_id")
    private Set<Model> models;
}
