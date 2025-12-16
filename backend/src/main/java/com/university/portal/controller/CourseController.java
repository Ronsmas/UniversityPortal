package com.university.portal.controller;

import com.university.portal.service.CourseService;
import com.university.portal.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            // Using manual getters
            courseService.registerStudent(request.getStudentId(), request.getCourseId());
            return ResponseEntity.ok("Registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

// NEW DROP ENDPOINT (Uses Query Params)
// Example URL: DELETE /api/drop?studentId=1&courseId=5
@DeleteMapping("/drop")
public void dropCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
    courseService.dropCourse(studentId, courseId);
}

    // Inner Class with Manual Getters
    public static class RegistrationRequest {
        private Long studentId;
        private Long courseId;
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long id) { this.studentId = id; }
        public Long getCourseId() { return courseId; }
        public void setCourseId(Long id) { this.courseId = id; }
    }
    // Endpoint to get courses for a specific student


@GetMapping("/student/{studentId}/courses")
public List<Course> getStudentCourses(@PathVariable Long studentId) {
    return courseService.getCoursesForStudent(studentId); 
}
}