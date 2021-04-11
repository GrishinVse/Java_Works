package com.edu.project.entity;

import com.edu.project.controller.RoleController;
import com.edu.project.service.RoleService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Client implements Serializable {

    public Client(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id")
    private long id;

    // Connection with User Profile
    //@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@PrimaryKeyJoinColumn
    @JoinColumn(name = "id")
    @JsonIgnore
    @Getter
    @Setter
    private ClientProfile clientProfile;

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean is_active;

    // Connection with Role
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role_id;

    public Client(String email, String password, Boolean is_active){
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.role_id = null;
    }

    public Client(String email, String password, Boolean is_active, Role role){
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.role_id = role;
    }

    public Client(String email, String password, Boolean is_active, Long id){
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        //Way to connect Client with Role entity
        setRole_id2(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientProfile=" + clientProfile +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_active=" + is_active +
                ", role_id=" + role_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public Long getRole_id(String name){
        Long loc_role = getRole_id().getId();

        return loc_role;
    }

    public void setRole_id2(Long id) {
        // find by id in Role

        //Way to connect Client with Role entity
        RoleController roleController = new RoleController(new RoleService());
        Role loc_role = roleController.find(Long.valueOf(id)).getBody().get();
        System.out.println(loc_role);

        this.role_id = loc_role;
    }
}
