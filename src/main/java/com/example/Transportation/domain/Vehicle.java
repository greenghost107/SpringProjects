package com.example.Transportation.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String licenseplate;

    @OneToMany(mappedBy="vehicle",targetEntity = Event.class,cascade = CascadeType.REMOVE)
    private List<Event> events;

    public Vehicle(){}

    public Vehicle(String name,String licensePlate)
    {
        this.name = name;
        this.licenseplate = licensePlate;
    }

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

    public String getLicensePlate() {
        return licenseplate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licenseplate = licensePlate;
    }
}
