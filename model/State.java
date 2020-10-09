package com.wolf.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "state")

public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long id;


    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id" ,updatable = false)
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
