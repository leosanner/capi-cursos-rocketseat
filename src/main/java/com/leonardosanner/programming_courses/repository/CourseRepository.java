package com.leonardosanner.programming_courses.repository;

import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findById(Long id);
    Optional<CourseEntity> findByName(String name);
    List<CourseEntity> findByCategory(String category);
    List<CourseEntity> findByNameAndCategory(String name, String category);
}
