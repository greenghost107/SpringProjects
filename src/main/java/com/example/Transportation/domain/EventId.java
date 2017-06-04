package com.example.Transportation.domain;

import java.io.Serializable;

/**
 * Created by Michael on 6/4/2017.
 */
public class EventId implements Serializable{
    private Long driver_id;
    private Long vehicle_id;

    public EventId(){}

    public EventId(Long driver_id,Long vehicle_id)
    {
        this.driver_id = driver_id;
        this.vehicle_id = vehicle_id;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
}
