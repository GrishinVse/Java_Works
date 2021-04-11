package com.edu.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Model implements Serializable {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    // Connection with Product
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "model")
    @PrimaryKeyJoinColumn
    private Set<Product> products;

    // Connection with Country
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country_id;

    public Country getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Country country_id) {
        this.country_id = country_id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Connection with Manufacturer
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Manufacturer brand_id;

    public Manufacturer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Manufacturer brand_id) {
        this.brand_id = brand_id;
    }

    @Column(name = "vendor_code")
    private String vendor_code;

    public String getVendor_code() {
        return vendor_code;
    }

    public void setVendor_code(String vendor_code) {
        this.vendor_code = vendor_code;
    }
}
