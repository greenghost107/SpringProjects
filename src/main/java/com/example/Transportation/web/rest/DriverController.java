package com.example.Transportation.web.rest;

import com.example.Transportation.exception.DriverNotFoundException;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.domain.Driver;
import com.example.Transportation.repository.DriverRepository;
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
@RequestMapping(value = "/drivers")
public class DriverController {
    private static final Logger log = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    ServiceImplementation service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Driver>> showAllDrivers() {
        return Optional.ofNullable(service.findAllDrivers())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Drivers In Drivers DB"));

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Driver> showDriverByID(@PathVariable("id")Long driverId)
    {
        return Optional.ofNullable(service.findDriverByID(driverId))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new DriverNotFoundException(driverId));
    }


    @RequestMapping(value = "/{name}",method = RequestMethod.POST)
    public ResponseEntity<Driver> addDriver(@PathVariable("name") String driverName)
    {
        return new ResponseEntity<>(service.addDriverByName(driverName),HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.DELETE)
    public ResponseEntity<Driver> deleteDriver(@PathVariable("name") String driverName)
    {
        return new ResponseEntity<>(service.deleteDriverByName(driverName),HttpStatus.OK);
    }
}
