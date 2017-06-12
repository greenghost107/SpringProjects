package com.example.Transportation.service;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.domain.Training;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 6/11/2017.
 */
@Service
public class TrainingService {
    private static final Logger log = LoggerFactory.getLogger(TrainingService.class);

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    DriverRepository driverRepository;

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }
    public Boolean deleteTraining(long trainingId) {
        Training training = trainingRepository.findOne(trainingId);
        if (training == null) {
            System.out.println("NO such training Found");
            return false;
        }
        trainingRepository.delete(trainingId);

        return true;
    }

    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }
}
