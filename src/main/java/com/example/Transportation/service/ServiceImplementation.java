package com.example.Transportation.service;

import com.example.Transportation.domain.*;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EventRepository;
import com.example.Transportation.repository.TrainingRepository;
import com.example.Transportation.repository.VehicleRepository;
import com.example.Transportation.web.rest.DriverController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import java.util.LinkedList;
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

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TrainingRepository trainingRepository;

    public List<Driver> findAllDrivers() {
        log.info("Started findAllDrivers");
        return driverRepository.findAll();
    }

    public List<Event> findAllEvents() {
        log.info("Started findAllEvents");
        return eventRepository.findAll();
    }

    public List<Vehicle> findAllVehicles() {
        log.info("Started findAllEvents");
        return vehicleRepository.findAll();
    }

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Transactional
    public Driver findDriverByID(Long id) {
        return driverRepository.findOne(id);
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
    public Vehicle addVehicleByName(String vehicleName, String licenseplate) {
        if (vehicleName == null) {
            System.out.println("Please supply A Vehicle Name");
            return null;
        } else if (!verifyOnlyLetters(vehicleName)) {
            System.out.println("Please supply only letters for Vehicle Name");
            return null;
        }

        if (licenseplate == null) {
            System.out.println("Please supply A License Plate");
            return null;
        }
        return vehicleRepository.save(new Vehicle(vehicleName, licenseplate));

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

    @Transactional
    public Vehicle deleteVehicleByName(String vehicleName) {
        List<Vehicle> vehicleList = vehicleRepository.findByName(vehicleName);
        if (vehicleList.size() == 0) {
            System.out.println("No Vehicle with this name");
            return null;
        }
        Vehicle vehicle = vehicleList.get(0);
        vehicleRepository.delete(vehicle);
        return vehicle;
    }

    @Transactional
    public List<Event> findAllEventsOfTypes(String eventType) {
        List<Event> returnedList = new LinkedList<>();
        List<Event> eventList = eventRepository.findAll();
        for (Event event:eventList){
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            if (discriminatorValue.value().equalsIgnoreCase(eventType))
            {
                returnedList.add(event);
            }
        }
        if (returnedList.size()==0) {
            return null;
        }
        return returnedList;
    }

    @Transactional
    public List<Event> findEventsByEventTypeAndDriver(String eventType,String driverName)
    {
        List<Event> returnedList = new LinkedList<>();
        List<Driver> driverList = driverRepository.findByName(driverName);
        if (driverList.size()==0)
        {
            System.out.println("No Drivers Found with this name");
            return null;
        }
        Driver driver = driverList.get(0);
        List<Event> eventList = eventRepository.findByDriver(driver);
        for (Event event:eventList)
        {
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            if (discriminatorValue.value().equalsIgnoreCase(eventType))
            {
                returnedList.add(event);
            }
        }
        if (returnedList.size()==0) {
            return null;
        }
        return returnedList;
    }


    public Ticket addTicket(long driver_id, long vehicle_id, String city, String street, float fine, String reasonForTicket) {
        Driver driver = driverRepository.findOne(driver_id);
        if (driver==null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle==null)
            return null;
        return eventRepository.save(new Ticket(driver,vehicle,city,street,fine,reasonForTicket));
    }

    public TrafficTicket addTrafficTicket(long driver_id, long vehicle_id, String city, String street, float fine, String reasonForTicket, String cause) {
        TrafficTicketEnum trafficTicketEnum = convertCauseToEnum(cause);
        Driver driver = driverRepository.findOne(driver_id);
        if (driver==null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle==null)
            return null;
        return eventRepository.save(new TrafficTicket(driver,vehicle,city,street,fine,reasonForTicket,trafficTicketEnum));
    }

    private TrafficTicketEnum convertCauseToEnum(String cause) {
        TrafficTicketEnum ans = null;
        if (cause.equalsIgnoreCase("SPEEDING"))
        {
            ans = TrafficTicketEnum.SPEEDING;
        }
        else if (cause.equalsIgnoreCase("RED_LIGHT_CROSSING"))
        {
            ans = TrafficTicketEnum.RED_LIGHT_CROSSING;
        }
        else if (cause.equalsIgnoreCase("NOT_GIVING_RIGHT_OF_WAY"))
        {
            ans = TrafficTicketEnum.NOT_GIVING_RIGHT_OF_WAY;
        }
        return ans;
    }

    public Accident addNewAccident(long driver_id, long vehicle_id, String city, String street, String driverLicense, String driverName,
                                   String otherDriverId, String carType, String carColor, String carNumber, String insuranceCompany)
    {
        Driver driver = driverRepository.findOne(driver_id);
        if (driver==null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle==null)
            return null;
        return eventRepository.save(new Accident(driver,vehicle,city,street,driverLicense,driverName,otherDriverId,carType,carColor,carNumber,insuranceCompany));
    }
}
