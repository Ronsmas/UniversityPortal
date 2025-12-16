package com.university.portal.repository;

import java.util.List;
import com.university.portal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // This custom query finds all courses that a specific student is registered for
@Query("SELECT r.course FROM Registration r WHERE r.student.id = :studentId")
List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
    
}
