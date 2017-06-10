package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.domain.Training;
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
 * Created by Michael on 10/06/2017.
 */
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    private static final Logger log = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private ServiceImplementation service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Enrollment>> showAllEnrollments()
    {
        log.info("Started showAllEnrollments");
        return Optional.ofNullable(service.findAllEnrollemtns())
                .map(enrol -> new ResponseEntity<>(enrol, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Enrollment DataBase Is Empty"));
    }

    @RequestMapping(value = "/driver/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Training>> getAllTrainingForDriver(@PathVariable("id") long id)
    {
        return Optional.ofNullable(service.findTrainingsForDriver(id))
                .map(driver -> new ResponseEntity<>(driver,HttpStatus.OK))
                .orElseThrow(() -> new SpringException("No driver with ID " + id + "was found"));
    }

}
