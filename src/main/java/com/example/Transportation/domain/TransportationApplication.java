package com.example.Transportation.domain;

import com.example.Transportation.repository.DriverRepository;
import com.example.Transportation.repository.EventRepository;
import com.example.Transportation.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.Transportation"})
public class TransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DriverRepository driverRepository, VehicleRepository vehicleRepository, EventRepository eventRepository){
		return (args)->
		{
			//clear the db
			if (driverRepository.count()>0)
				driverRepository.deleteAll();

			if (vehicleRepository.count()>0)
				vehicleRepository.deleteAll();

			if (eventRepository.count()>0)
				eventRepository.deleteAll();


			driverRepository.save(new Driver("avi"));
			driverRepository.save(new Driver("igor"));
			driverRepository.save(new Driver("sami"));


			vehicleRepository.save(new Vehicle("Audi","423-441"));
			vehicleRepository.save(new Vehicle("Subaru","556-987"));
			vehicleRepository.save(new Vehicle("BMW","147-845"));

			Driver driver = driverRepository.findByName("avi").get(0);
			Vehicle vehicle = vehicleRepository.findByName("Audi").get(0);
			eventRepository.save(new Event(driver,vehicle,"Ernest Simon","1"));

		};
	}

}
