package com.example.Transportation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
public interface VehicleRepository extends JpaRepository<Vehicle,Long>
{
    List<Vehicle> findByName(String driverName);
}
