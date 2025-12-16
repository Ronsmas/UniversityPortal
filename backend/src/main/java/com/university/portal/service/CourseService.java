package com.university.portal.service;

import com.university.portal.entity.Course;
import com.university.portal.entity.Registration;
import com.university.portal.entity.Student;
import com.university.portal.repository.CourseRepository;
import com.university.portal.repository.RegistrationRepository;
import com.university.portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    /**
     * Dependency Injection (DI) is a design pattern where the control of creating and managing
     * objects (dependencies) is inverted and given to a container or framework. Instead of a
     * component creating its dependencies, the framework "injects" them.
     * Here, the Spring framework creates instances of the repositories (CourseRepository,
     * StudentRepository, RegistrationRepository) and provides them to the CourseService,
     * decoupling the service from the direct instantiation of its dependencies.
     */
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Registration registerStudent(Long studentId, Long courseId) {
        // Check if the student is already registered for the course to prevent duplicates
        Optional<Registration> existingRegistration = registrationRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (existingRegistration.isPresent()) {
            throw new IllegalStateException("Student is already registered for this course.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        Registration newRegistration = new Registration();
        newRegistration.setStudent(student);
        newRegistration.setCourse(course);
        newRegistration.setRegistrationDate(new Date());

        return registrationRepository.save(newRegistration);
    }
    
public void dropCourse(Long studentId, Long courseId) {
    registrationRepository.deleteByStudentIdAndCourseId(studentId, courseId);
}
    public List<Course> getCoursesForStudent(Long studentId) {
        // Calls the Repository method we just created
        return courseRepository.findCoursesByStudentId(studentId);
    }
}
