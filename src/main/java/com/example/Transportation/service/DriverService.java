package com.example.Transportation.service;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.domain.Event;
import com.example.Transportation.domain.Training;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EnrollmentRepository;
import com.example.Transportation.repository.TrainingRepository;
import com.example.Transportation.web.rest.DriverController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Michael on 6/11/2017.
 */
@Service
public class DriverService {
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;


    public List<Driver> findAllDrivers() {
        log.info("Started findAllDrivers");
        return driverRepository.findAll();
    }

    @Transactional
    public Driver addDriverByName(String driverName) {
        if (driverName == null) {
            System.out.println("Please supply A Driver Name");
            return null;
        } else if (!verifyOnlyLetters(driverName)) {
            System.out.println("Please supply only letters for Driver Name");
            return null;
        }
        return driverRepository.save(new Driver(driverName));

    }

    private boolean verifyOnlyLetters(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @Transactional
    public Driver deleteDriverByName(String driverName) {
        List<Driver> driver_list = driverRepository.findByName(driverName);
        if (driver_list.size() == 0) {
            System.out.println("No Driver with this name");
            return null;
        }
        Driver driver = driver_list.get(0);
        driverRepository.delete(driver);
        return driver;
    }




    public Integer calcBonusForDriver(long driverId) {
        Driver driver = driverRepository.findOne(driverId);
        List<Event> driverEvents = driver.getEvents();
        List<Enrollment> driverTrainings = driver.getEnrollments();
        int points = driverTrainings.size();
        for(Event event:driverEvents)
        {
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            points -= pointsDeductedForEvent(discriminatorValue.value());
        }
        return points*100;

    }

    private int pointsDeductedForEvent(String event)
    {
        int returnVal = 0;
        if (event.equalsIgnoreCase("Accident")){
            returnVal =3;
        }
        else if (event.equalsIgnoreCase("TrafficTicket"))
        {
            returnVal =2;
        }
        else if (event.equalsIgnoreCase("Ticket"))
        {
            returnVal =1;
        }
        return returnVal;
    }



    @Transactional
    public Driver findDriverByID(Long id) {
        return driverRepository.findOne(id);
    }

    public Enrollment registerDriverToTraining(long driverId, long trainingId) {
        Driver driver = driverRepository.findOne(driverId);
        Training training = trainingRepository.findOne(trainingId);
        return enrollmentRepository.save(new Enrollment(driver, training));
    }

    public Boolean deleteDriverFromTraining(long driverId, long trainingId) {

        Driver driver = driverRepository.findOne(driverId);
        Training training = trainingRepository.findOne(trainingId);
        boolean ans = false;
//        enrollmentRepository.findByDriver(driver).stream().filter(enrollments -> {
//            if (enrollments.getTraining().equals(training)) {
//                enrollmentRepository.delete(enrollments);
//
//            }
//
//        });
        List<Enrollment> enrollmentList = enrollmentRepository.findByDriver(driver);
        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.getTraining().equals(training)) {
                enrollmentRepository.delete(enrollment);
                ans = true;
                break;
            }
        }

        return ans;
    }
}
