package com.example.Transportation.domain;

import javax.persistence.*;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long driver_id;
    private Long vehicle_id;

    private String city;

    private String street;

    @ManyToOne
    @JoinColumn(name="driver_id",insertable = false, updatable = false)
    private Driver driver;


    @ManyToOne
    @JoinColumn(name="vehicle_id",insertable = false, updatable = false)
    private Vehicle vehicle;



    public Event(){}

    public Event(Driver driver,Vehicle vehicle,String city,String street)
    {
        this.driver_id = driver.getDriver_id();
        this.vehicle_id = vehicle.getId();
        this.driver = driver;
        this.vehicle = vehicle;
        this.city = city;
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
