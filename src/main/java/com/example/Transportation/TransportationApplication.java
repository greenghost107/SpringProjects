package com.example.Transportation;

import com.example.Transportation.domain.*;
import com.example.Transportation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.Transportation"})
public class TransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DriverRepository driverRepository, VehicleRepository vehicleRepository, EventRepository eventRepository, TrainingRepository trainingRepository,
								  EnrollmentRepository enrollmentRepository){
		return (args)->
		{
			//clear the db
			if (driverRepository.count()>0)
				driverRepository.deleteAll();

			if (vehicleRepository.count()>0)
				vehicleRepository.deleteAll();

			if (eventRepository.count()>0)
				eventRepository.deleteAll();

			if (trainingRepository.count()>0)
				trainingRepository.deleteAll();

			if (enrollmentRepository.count()>0)
				enrollmentRepository.deleteAll();


			driverRepository.save(new Driver("avi"));
			driverRepository.save(new Driver("igor"));
			driverRepository.save(new Driver("sami"));


			vehicleRepository.save(new Vehicle("Audi","423-441"));
			vehicleRepository.save(new Vehicle("Subaru","556-987"));
			vehicleRepository.save(new Vehicle("BMW","147-845"));

			Driver driver = driverRepository.findByName("avi").get(0);
			Vehicle vehicle = vehicleRepository.findByName("Audi").get(0);
			eventRepository.save(new Ticket(driver,vehicle,"Be'er Sheva","Ernest Simon",12,"interrupted the traffic"));
			 driver = driverRepository.findByName("igor").get(0);
			 vehicle = vehicleRepository.findByName("Audi").get(0);
			eventRepository.save(new TrafficTicket(driver,vehicle,"Be'er Sheva","Ernest Simon", (float) 54.5,"interrupted the traffic",TrafficTicketEnum.RED_LIGHT_CROSSING));

			eventRepository.save(new Accident(driver,vehicle,"Be'er Sheva","Ernest Simon","","AbNaseraladin","8585466","Meclaren","Red","87-89-882","bituah yashir"));
			LocalDate localDate = LocalDate.of(2017,2,15);
			trainingRepository.save(new Training("bilbul moah",localDate));
			Training training = trainingRepository.save(new Training("continuing education program",LocalDate.of(2017,4,1)));
			enrollmentRepository.save(new Enrollment(driver,training));
			driver = driverRepository.findByName("sami").get(0);
			enrollmentRepository.save(new Enrollment(driver,training));



		};
	}

}
