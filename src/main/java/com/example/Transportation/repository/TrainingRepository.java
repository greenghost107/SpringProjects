package com.example.Transportation.repository;

import com.example.Transportation.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 6/6/2017.
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {
    List<Training> findById(long id);
}
