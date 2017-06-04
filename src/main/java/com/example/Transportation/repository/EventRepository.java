package com.example.Transportation.repository;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Event;
import com.example.Transportation.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDriver(Driver driver);
    List<Event> findByVehicle(Vehicle vehicle);
}
