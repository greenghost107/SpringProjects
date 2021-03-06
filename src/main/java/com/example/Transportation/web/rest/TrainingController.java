package com.example.Transportation.web.rest;

import com.example.Transportation.domain.Training;
import com.example.Transportation.exception.SpringException;
import com.example.Transportation.service.TrainingService;
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
@RequestMapping(value = "/training")
public class TrainingController {
    private static final Logger log = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    TrainingService trainingService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Training>> showAllTrainings() {
        return Optional.ofNullable(trainingService.findAllTrainings())
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("no Trainings In DB"));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Training> addTraining(@RequestBody Training training)
    {
        return new ResponseEntity<>(trainingService.addTraining(training),HttpStatus.OK);
    }


    @RequestMapping(value = "/{trainingId}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteTraining(@PathVariable("trainingId") long trainingId)
    {
        return new ResponseEntity<>(trainingService.deleteTraining(trainingId),HttpStatus.OK);
    }


}
