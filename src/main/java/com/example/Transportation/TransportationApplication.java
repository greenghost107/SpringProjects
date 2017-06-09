package com.example.Transportation;

import com.example.Transportation.domain.*;
import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EventRepository;
import com.example.Transportation.repository.TrainingRepository;
import com.example.Transportation.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.Transportation"})
public class TransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DriverRepository driverRepository, VehicleRepository vehicleRepository, EventRepository eventRepository, TrainingRepository trainingRepository){
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

			Training training = trainingRepository.save(new Training("continuing education program",new Date(117,4,1)));
//			driver.registerTraining(training);


		};
	}

}
