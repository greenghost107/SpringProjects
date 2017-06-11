package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.exception.DriverNotFoundException;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    DriverService driverService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Driver>> showAllDrivers() {
        return Optional.ofNullable(driverService.findAllDrivers())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Drivers In Drivers DB"));

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Driver> showDriverByID(@PathVariable("id")Long driverId)
    {
        return Optional.ofNullable(driverService.findDriverByID(driverId))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new DriverNotFoundException(driverId));
    }


    @RequestMapping(value = "/{name}",method = RequestMethod.POST)
    public ResponseEntity<Driver> addDriver(@PathVariable("name") String driverName)
    {
        return new ResponseEntity<>(driverService.addDriverByName(driverName),HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.DELETE)
    public ResponseEntity<Driver> deleteDriver(@PathVariable("name") String driverName)
    {
        return new ResponseEntity<>(driverService.deleteDriverByName(driverName),HttpStatus.OK);
    }

    @RequestMapping(value = "/{driverId}/{trainingId}",method = RequestMethod.POST)
    public ResponseEntity<Enrollment> registerDriverToTraining(@PathVariable("driverId") long driverId,@PathVariable("trainingId") long trainingId)
    {
        return new ResponseEntity<>(driverService.registerDriverToTraining(driverId,trainingId),HttpStatus.OK);
    }

    @RequestMapping(value = "/{driverId}/{trainingId}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteDriverFromTraining(@PathVariable("driverId") long driverId,@PathVariable("trainingId") long trainingId)
    {
        return new ResponseEntity<>(driverService.deleteDriverFromTraining(driverId,trainingId),HttpStatus.OK);
    }

    @RequestMapping(value = "/{driverId}/calcBonus",method = RequestMethod.GET)
    public ResponseEntity<Integer> calcBonus(@PathVariable("driverId") long driverId)
    {
        return Optional.ofNullable(driverService.calcBonusForDriver(driverId))
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Drivers In Drivers DB"));
    }

}
