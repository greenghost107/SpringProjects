package com.example.Transportation.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Michael on 6/5/2017.
 */
@Entity
@Table(name="TrafficTicket")
public class TrafficTicket extends Ticket{
    public String cause;

    public TrafficTicket(){}

    public TrafficTicket(Driver driver, Vehicle vehicle, String city, String street, float fine, String reasonForTicket, String cause)
    {
        super(driver,vehicle,city,street,fine,reasonForTicket);
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
