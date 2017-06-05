package com.example.Transportation.service;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Event;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EventRepository;
import com.example.Transportation.web.rest.DriverController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Michael on 6/5/2017.
 */
@Service
public class ServiceImplementation {
    private static final Logger log = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    EventRepository eventRepository;

    public List<Driver> findAllDrivers() {
        log.info("Started findAllDrivers");
        return driverRepository.findAll();
    }

    public List<Event> findAllEvents() {
        log.info("Started findAllEvents");
        return eventRepository.findAll();
    }

    @Transactional
    public Driver findDriverByID(Long id) {
        return driverRepository.findOne(id);
    }

    @Transactional
    public Driver addDriverByName(String driverName) {
        if (driverName ==null)
        {
            System.out.println("Please supply A Driver Name");
            return null;
        }
        else if (!verifyOnlyLetters(driverName))
        {
            System.out.println("Please supply only letters for Driver Name");
            return null;
        }
        return driverRepository.save(new Driver(driverName));

    }

    private boolean verifyOnlyLetters(String name) {
        return name.matches("[a-zA-Z]+");
    }


    @Transactional
    public Driver deleteDriverByName(String driverName)
    {
        List<Driver> driver_list = driverRepository.findByName(driverName);
        if (driver_list.size()==0)
        {
            System.out.println("No Driver with this name");
            return null;
        }
        Driver driver = driver_list.get(0);
        driverRepository.delete(driver);
        return driver;
    }
}
