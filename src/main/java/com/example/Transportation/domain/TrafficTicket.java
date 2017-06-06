package com.example.Transportation.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Michael on 6/5/2017.
 */
@Entity
@Table(name="TrafficTicket")
@DiscriminatorValue("TrafficTicket")
public class TrafficTicket extends Ticket{

    @Enumerated(EnumType.STRING)
    private TrafficTicketEnum cause;

    public TrafficTicket(){}

    public TrafficTicket(Driver driver, Vehicle vehicle, String city, String street, float fine, String reasonForTicket, TrafficTicketEnum cause)
    {
        super(driver,vehicle,city,street,fine,reasonForTicket);
        this.cause = cause;
    }

    public TrafficTicketEnum getCause() {
        return cause;
    }

    public void setCause(TrafficTicketEnum cause) {
        this.cause = cause;
    }
}
