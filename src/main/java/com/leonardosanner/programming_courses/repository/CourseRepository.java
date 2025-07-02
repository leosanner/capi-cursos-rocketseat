package com.leonardosanner.programming_courses.repository;

import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findById(Long id);
}
