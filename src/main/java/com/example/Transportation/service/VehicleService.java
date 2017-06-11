package com.example.Transportation.service;

import com.example.Transportation.domain.Vehicle;
import com.example.Transportation.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Michael on 6/11/2017.
 */
@Service
public class VehicleService {
    private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    VehicleRepository vehicleRepository;

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

    private boolean verifyOnlyLetters(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public List<Vehicle> findAllVehicles() {
        log.info("Started findAllEvents");
        return vehicleRepository.findAll();
    }
}

