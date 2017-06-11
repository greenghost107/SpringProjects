package com.example.Transportation.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Driver {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long driver_id;

    @NotNull
    @Size(min = 2,max = 15)
    private String name;

    @OneToMany(mappedBy="driver",targetEntity = Event.class,cascade = CascadeType.REMOVE)
    private List<Event> events;

    @OneToMany(mappedBy="driver",targetEntity = Enrollment.class,cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    public Driver(){}

    public Driver(String name)
    {
        this.name = name;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public List<Event> getEvents() {
        return events;
    }
}
