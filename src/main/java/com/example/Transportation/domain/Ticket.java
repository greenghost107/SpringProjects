package com.example.Transportation.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Michael on 6/5/2017.
 */
@Entity
@Table(name="Ticket")
@DiscriminatorValue("Ticket")
public class Ticket extends Event {

    private float fine;

    @Size(max = 1000)
    private String notes;

    public Ticket(){}

    public Ticket(Driver driver, Vehicle vehicle, String city, String street, float fine, String reasonForTicket){
        super(driver,vehicle,city,street);
        this.fine = fine;
        this.notes = reasonForTicket;
    }
    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
