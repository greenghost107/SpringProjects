package com.example.Transportation.domain;

import javax.persistence.*;

/**
 * Created by Michael on 09/06/2017.
 */
@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {

    @Id
    private Long driver_id;

    @Id
    private Long training_id;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="driver_id",insertable = false, updatable = false)
    private Driver driver;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="training_id",insertable = false, updatable = false)
    private Training training;

    public Enrollment() {}

    public Enrollment(Driver driver,Training training) {
        this.driver_id = driver.getDriver_id();
        this.training_id = training.getId();
        this.driver = driver;
        this.training = training;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Long getTraining_id() {
        return training_id;
    }

    public void setTraining_id(Long training_id) {
        this.training_id = training_id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
