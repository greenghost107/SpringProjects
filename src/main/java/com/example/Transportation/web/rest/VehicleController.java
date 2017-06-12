package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Vehicle;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> showAllVehicles() {
        return Optional.ofNullable(vehicleService.findAllVehicles())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Vehicles In Drivers DB"));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> addVehicle(@RequestParam("vehicleName") String vehicleName, @RequestParam("licenseplate") String licenseplate) {
        Vehicle vehicle = vehicleService.addVehicleByName(vehicleName, licenseplate);
        if (vehicle ==null){
            log.warn("Could Not add vehicle to db");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            log.info("Added Vehicle to DB");
            return new ResponseEntity<Vehicle>(vehicle,HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Vehicle> deleteVehicleByName(@RequestParam("vehicleName") String vehicleName) {
        Vehicle vehicle = vehicleService.deleteVehicleByName(vehicleName);
        if (vehicle == null) {
            log.warn("could not delete the vehicle {} from DB");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            log.info("Deleted protector {} from DB", vehicle.getName());
            return new ResponseEntity<>(vehicle, HttpStatus.OK);

        }
    }
}
