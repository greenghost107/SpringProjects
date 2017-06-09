package com.example.Transportation.domain;

import java.io.Serializable;

/**
 * Created by Michael on 09/06/2017.
 */
public class EnrollmentId implements Serializable {
    private Long driver_id;
    private Long training_id;

    public EnrollmentId(){}

    public EnrollmentId(Long driver_id, Long training_id) {
        this.driver_id = driver_id;
        this.training_id = training_id;
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
}
