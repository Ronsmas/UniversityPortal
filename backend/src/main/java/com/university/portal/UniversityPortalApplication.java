package com.university.portal;

import com.university.portal.entity.Course;
import com.university.portal.entity.Student;
import com.university.portal.repository.CourseRepository;
import com.university.portal.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityPortalApplication.class, args);
	}

    // This runs automatically when the server starts to add data
    @Bean
    public CommandLineRunner demo(CourseRepository courseRepo, StudentRepository studentRepo) {
        return (args) -> {
            // 1. Create a Test Student (so we can register)
            Student student = new Student();
            student.setName("Kisii Student");
            student.setEmail("student@kisii.ac.ke");
            studentRepo.save(student);

            // 2. Create Course 1
            Course c1 = new Course();
            c1.setTitle("Software Engineering II");
            c1.setCredits(4);
            c1.setFee(15000.0);
            c1.setCurrency("KES");
            courseRepo.save(c1);

            // 3. Create Course 2
            Course c2 = new Course();
            c2.setTitle("Data Structures & Algorithms");
            c2.setCredits(3);
            c2.setFee(12000.0);
            c2.setCurrency("KES");
            courseRepo.save(c2);

            // 4. Create Course 3
            Course c3 = new Course();
            c3.setTitle("Computer Security");
            c3.setCredits(4);
            c3.setFee(18000.0);
            c3.setCurrency("KES");
            courseRepo.save(c3);
        };
    }
}