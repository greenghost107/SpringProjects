package com.example.Transportation.service;

import com.example.Transportation.domain.*;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EventRepository;
import com.example.Transportation.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 6/11/2017.
 */
@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    EventRepository eventRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Event> findAllEvents() {
        log.info("Started findAllEvents");
        return eventRepository.findAll();
    }

    @Transactional
    public List<Event> findAllEventsOfTypes(String eventType) {
        List<Event> returnedList = new LinkedList<>();
        List<Event> eventList = eventRepository.findAll();
        for (Event event : eventList) {
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            if (discriminatorValue.value().equalsIgnoreCase(eventType)) {
                returnedList.add(event);
            }
        }
        if (returnedList.size() == 0) {
            return null;
        }
        return returnedList;
    }


    @Transactional
    public List<Event> findEventsByEventTypeAndDriver(String eventType, String driverName) {
        List<Event> returnedList = new LinkedList<>();
        List<Driver> driverList = driverRepository.findByName(driverName);
        if (driverList.size() == 0) {
            System.out.println("No Drivers Found with this name");
            return null;
        }
        Driver driver = driverList.get(0);
        List<Event> eventList = eventRepository.findByDriver(driver);
        for (Event event : eventList) {
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            if (discriminatorValue.value().equalsIgnoreCase(eventType)) {
                returnedList.add(event);
            }
        }
        if (returnedList.size() == 0) {
            return null;
        }
        return returnedList;
    }

    public TrafficTicket addTrafficTicket(long driver_id, long vehicle_id, String city, String street, float fine, String reasonForTicket, String cause) {
        TrafficTicketEnum trafficTicketEnum = convertCauseToEnum(cause);
        Driver driver = driverRepository.findOne(driver_id);
        if (driver == null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle == null)
            return null;
        return eventRepository.save(new TrafficTicket(driver, vehicle, city, street, fine, reasonForTicket, trafficTicketEnum));
    }

    private TrafficTicketEnum convertCauseToEnum(String cause) {
        TrafficTicketEnum ans = null;
        if (cause.equalsIgnoreCase("SPEEDING")) {
            ans = TrafficTicketEnum.SPEEDING;
        } else if (cause.equalsIgnoreCase("RED_LIGHT_CROSSING")) {
            ans = TrafficTicketEnum.RED_LIGHT_CROSSING;
        } else if (cause.equalsIgnoreCase("NOT_GIVING_RIGHT_OF_WAY")) {
            ans = TrafficTicketEnum.NOT_GIVING_RIGHT_OF_WAY;
        }
        return ans;
    }

    public Accident addNewAccident(long driver_id, long vehicle_id, String city, String street, String driverLicense, String driverName,
                                   String otherDriverId, String carType, String carColor, String carNumber, String insuranceCompany) {
        Driver driver = driverRepository.findOne(driver_id);
        if (driver == null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle == null)
            return null;
        return eventRepository.save(new Accident(driver, vehicle, city, street, driverLicense, driverName, otherDriverId, carType, carColor, carNumber, insuranceCompany));
    }

    public Ticket addTicket(long driver_id, long vehicle_id, String city, String street, float fine, String reasonForTicket) {
        Driver driver = driverRepository.findOne(driver_id);
        if (driver == null)
            return null;
        Vehicle vehicle = vehicleRepository.findOne(vehicle_id);
        if (vehicle == null)
            return null;
        return eventRepository.save(new Ticket(driver, vehicle, city, street, fine, reasonForTicket));
    }

    public Boolean deleteEventForDriver(long eventId) {


        Event event = eventRepository.findOne(eventId);
        if (event == null) {
            System.out.println("NO such event Found");
            return false;
        }
        eventRepository.delete(eventId);

        return true;
    }

    public HashMap<String, Float> calcBreakdown() {

        HashMap<String, Float> eventBreakdown = initializeWithventTypes();
        List<Event> eventList = eventRepository.findAll();
        for (Event event : eventList) {
            DiscriminatorValue discriminatorValue = event.getClass().getAnnotation(DiscriminatorValue.class);
            float temp = eventBreakdown.get(discriminatorValue.value());
            eventBreakdown.replace(String.valueOf(discriminatorValue.value()), temp + 1);
        }
        for (String key : eventBreakdown.keySet()) {
            eventBreakdown.replace(key, (eventBreakdown.get(key) * 100) / eventBreakdown.size());
        }
        return eventBreakdown;
    }
    private HashMap<String, Float> initializeWithventTypes() {
        HashMap<String, Float> returnedMap = new HashMap<>();
        returnedMap.put("Accident", 0f);
        returnedMap.put("TrafficTicket", 0f);
        returnedMap.put("Ticket", 0f);
        return returnedMap;
    }

}
