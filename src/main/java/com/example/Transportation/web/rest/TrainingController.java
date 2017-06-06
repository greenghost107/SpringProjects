package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Training;
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
 * Created by Michael on 6/6/2017.
 */
@RestController
@RequestMapping(value = "/training")
public class TrainingController {
    private static final Logger log = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    ServiceImplementation service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Training>> showAllTrainings() {
        return Optional.ofNullable(service.findAllTrainings())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Trainings In DB"));

    }


}
