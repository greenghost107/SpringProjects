package com.example.Transportation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 6/4/2017.
 */
@Repository
public interface EventsRepository extends JpaRepository<Events,Long>{

    List<Events> findByName(String name);
}
