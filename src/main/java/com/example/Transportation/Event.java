package com.example.Transportation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Michael on 6/4/2017.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Events event_id;

    public Event(){}

    public Event(String eventName, Events events)
    {
        this.name = eventName;
        this.event_id = events;
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

    public Events getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Events event_id) {
        this.event_id = event_id;
    }
}
