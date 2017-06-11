package com.example.Transportation.service;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.domain.Training;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EnrollmentRepository;
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
public class EnrollmentService {
    private static final Logger log = LoggerFactory.getLogger(EnrollmentService.class);
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    TrainingRepository trainingRepository;

    public List<Enrollment> findAllEnrollemtns() {
        return enrollmentRepository.findAll();
    }





    public List<Training> findTrainingsForDriver(long driverId) {
        Driver driver = driverRepository.findOne(driverId);

        List<Enrollment> enrollments = driver.getEnrollments();
        List<Training> returnedList = new LinkedList<>();
        for (Enrollment enrollment : enrollments) {
            returnedList.add(enrollment.getTraining());
        }
        return returnedList;

    }
}
