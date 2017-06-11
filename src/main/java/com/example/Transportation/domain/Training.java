package com.example.Transportation.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 6/6/2017.
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@training")
@Table(name="training")
public class Training {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long id;

    private String description;

    private LocalDate dateOfTraining;

    @OneToMany(mappedBy="training",targetEntity = Enrollment.class,cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    public Training(){}

    public Training(String description,LocalDate date)
    {
        this.description = description;
        this.dateOfTraining = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(LocalDate dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }
}
