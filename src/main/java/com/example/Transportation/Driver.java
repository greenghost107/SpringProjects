package com.example.Transportation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<Training> trainings;

    public Driver(){}

    public Driver(String name)
    {
        this.name = name;
    }
}
