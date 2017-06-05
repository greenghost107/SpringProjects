package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Event;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.service.ServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Event>> showAllDrivers() {
        return Optional.ofNullable(service.findAllEvents())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Events In Drivers DB"));

    }
}
