package com.example.Transportation.web.rest;

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
 * Created by Michael on 6/6/2017.
 */
@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    ServiceImplementation service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> showAllVehicles() {
        return Optional.ofNullable(service.findAllVehicles())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Vehicles In Drivers DB"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Vehicle> addVehicle(@RequestParam String vehicleName, @RequestParam String licenseplate)
    {
        return new ResponseEntity<>(service.addVehicleByName(vehicleName,licenseplate),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Vehicle> deleteVehicleByName(@RequestParam String vehicleName)
    {
        return new ResponseEntity<>(service.deleteVehicleByName(vehicleName),HttpStatus.OK);
    }
}
