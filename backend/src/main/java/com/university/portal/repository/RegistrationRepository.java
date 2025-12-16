package com.university.portal.repository;

import com.university.portal.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    // 1. Find a specific registration (used internally if needed)
    Optional<Registration> findByStudentIdAndCourseId(Long studentId, Long courseId);

    // 2. DELETE a specific registration (The new method)
    @Transactional
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
}