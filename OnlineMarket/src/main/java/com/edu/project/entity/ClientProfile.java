package com.edu.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class ClientProfile implements Serializable {

    public ClientProfile(){}

    public ClientProfile(Client client, String first_name, String last_name, String father_name){
        this.client = client;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.address = null;
        this.birth_date = null;
    }

    public ClientProfile(String first_name, String last_name, String father_name, String address, Date birth_date){
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.address = address;
        this.birth_date = birth_date;
    }

    @Id
    @Column(name = "client_id")
    private long id;

    // Connection with User Profile

    @MapsId
    //@JoinColumn(name = "client_id")
    @OneToOne(fetch = FetchType.EAGER)
    //@OneToOne
    @JsonIgnore
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Column(name = "first_name")
    private String first_name;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    private String last_name;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "father_name")
    private String father_name;

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    /*
    @Transient
    private String full_name;

    public void setFull_name() {
        this.full_name = getLast_name() + " " + getFirst_name() + " " + getFather_name();
    }

    public String getFull_name() {
        return full_name;
    }

     */

    @Column(name = "address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "birth_date")
    private Date birth_date;

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
}
