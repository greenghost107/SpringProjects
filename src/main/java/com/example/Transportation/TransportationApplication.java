package com.example.Transportation;

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
	public CommandLineRunner demo(DriverRepository driverRepository,VehicleRepository vehicleRepository){
		return (args)->
		{
			//clear the db
			if (driverRepository.count()>0)
				driverRepository.deleteAll();


			driverRepository.save(new Driver("avi"));
			driverRepository.save(new Driver("igor"));
			driverRepository.save(new Driver("sami"));
		};
	}
//	@Bean
//	public CommandLineRunner demo(CourseRepository courserepository, DepartmentRepository departmentrepository, StudentRepository studentRepository) {
//		return (args) -> {
//			if (courserepository.count() > 0) {
//				courserepository.deleteAll();
//			}
//			if (departmentrepository.count() > 0) {
//				departmentrepository.deleteAll();
//			}
//			if (studentRepository.count() > 0) {
//				studentRepository.deleteAll();
//			}
//
//			// save a couple of departments
//			departmentrepository.save(new Department("Computer Science"));
//			departmentrepository.save(new Department("Software Engineering"));
//			departmentrepository.save(new Department("Information Systems"));
//
//			// save a couple of courses
//			courserepository.save(new Course("Operating Systems",  departmentrepository.findByName("Computer Science").get(0)));
//			courserepository.save(new Course("Logic Programming",  departmentrepository.findByName("Computer Science").get(0)));
//			courserepository.save(new Course("Data Structures",  departmentrepository.findByName("Computer Science").get(0)));
//
//
//			courserepository.save(new Course("Object Oriented Programming",  departmentrepository.findByName("Software Engineering").get(0)));
//			courserepository.save(new Course("Introduction to Software Engineering",  departmentrepository.findByName("Software Engineering").get(0)));
//			courserepository.save(new Course("Principles of Programming Languages",  departmentrepository.findByName("Software Engineering").get(0)));
//
//			courserepository.save(new Course("Introduction to Computing Systems",  departmentrepository.findByName("Information Systems").get(0)));
//			courserepository.save(new Course("File Organization and Processing",  departmentrepository.findByName("Information Systems").get(0)));
//
//
//
//
//			studentRepository.save(new Student("Michael"));
//			studentRepository.save(new Student("Itai"));
//			studentRepository.save(new Student("Roi"));
//			studentRepository.save(new Student("Tz"));
//
//
//
//
//		};
//
//	}

}
