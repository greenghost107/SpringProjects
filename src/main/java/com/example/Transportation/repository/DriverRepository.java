package com.example.Transportation.repository;

import com.example.Transportation.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{
    List<Driver> findByName(String driverName);
}
