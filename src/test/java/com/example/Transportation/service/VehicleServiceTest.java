package com.example.Transportation.service;

import com.example.Transportation.TransportationApplication;
import com.example.Transportation.domain.Vehicle;
import com.example.Transportation.repository.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michael on 6/12/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TransportationApplication.class})
@EnableConfigurationProperties
@Transactional
public class VehicleServiceTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Before
    private void setup(){
        //insert requested
        //todo - tests are incorrect, here we need to insert data to the db
    }

    @After
    private void end(){
        //delete inserted
        //here we need to clean the repo
    }

    @Test
    public void findAllVehicles() throws Exception {
       List<Vehicle> vehicles = vehicleService.findAllVehicles();

       for (Vehicle vehicle:vehicles)
       {
            List<Vehicle> inner_list = vehicleRepository.findByName(vehicle.getName());
            assertTrue(inner_list.size()>0);
       }
    }

    @Test
    public void deleteVehicleByName() throws Exception {
        Vehicle vehicle = vehicleService.deleteVehicleByName("stam");
        assertNull(vehicle);
    }

    @Test
    public void addVehicleByName() throws Exception {
        Vehicle vehicle = vehicleService.addVehicleByName("test1","123-456-789");
        assertNotNull(vehicleRepository.findByName(vehicle.getName()));
    }

}