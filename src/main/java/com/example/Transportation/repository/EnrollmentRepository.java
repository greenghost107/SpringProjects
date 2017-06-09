package com.example.Transportation.repository;

import com.example.Transportation.domain.Driver;
import com.example.Transportation.domain.Enrollment;
import com.example.Transportation.domain.EnrollmentId;
import com.example.Transportation.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 09/06/2017.
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,EnrollmentId> {

        List<Enrollment> findByDriver(Driver driver);
        List<Enrollment> findByTraining(Training training);

        }
