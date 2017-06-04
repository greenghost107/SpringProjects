package com.example.Transportation;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Michael on 6/4/2017.
 */
public class Events {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Driver driver;

    private Vehicle vehicle;
}
