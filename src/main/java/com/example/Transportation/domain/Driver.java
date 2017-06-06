package com.example.Transportation.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@driver")
public class Driver {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2,max = 15)
    private String name;

    @OneToMany(mappedBy="driver",targetEntity = Event.class,cascade = CascadeType.REMOVE)
    private List<Event> events;


//    private List<Training> trainings;

    public Driver(){}

    public Driver(String name)
    {
        this.name = name;
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

//    public Training addTraining(Training training)
//    {
//        this.trainings.add(training);
//        return training;
//    }
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "driver_training", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "training_id") })
//    public List<Training> getTrainings(){
//        return this.trainings;
//    }
}
