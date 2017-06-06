package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Event;
import com.example.Transportation.domain.Ticket;
import com.example.Transportation.domain.Vehicle;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.service.ServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 6/5/2017.
 */
@RestController
@RequestMapping(value = "/events")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    ServiceImplementation service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Event>> showAllEvents() {
        return Optional.ofNullable(service.findAllEvents())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Events In Drivers DB"));

    }

    //	Show events of type X and driver Y (x,y are parameters)
    @RequestMapping(value = "/getspecific",method = RequestMethod.GET)
    public ResponseEntity<List<Event>> showEventsOfTypeXandDriverY(@RequestParam(name = "event") String eventType, @RequestParam(name = "driver_name") String driver_name)
    {
        return Optional.ofNullable(service.findEventsByEventTypeAndDriver(eventType,driver_name))
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no " + eventType + " were found in Events DB"));
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEventsFromType(@RequestParam(name = "event") String eventType)
    {
        return Optional.ofNullable(service.findAllEventsOfTypes(eventType))
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no " + eventType + " were found in Events DB"));
    }


//    @RequestMapping(value = "/Ticket",method = RequestMethod.POST)
//    public Ticket addTicket(@RequestParam long driver_id, long vehicle, String city, String street, float fine, String reasonForTicket)
//    @RequestMapping(value = "/TrafficTicket",method = RequestMethod.POST)
//    @RequestMapping(value = "/Accident",method = RequestMethod.POST)


}
