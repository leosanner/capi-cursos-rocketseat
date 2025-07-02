package com.leonardosanner.programming_courses.controller.course;


import com.leonardosanner.programming_courses.dto.course.UpdateCourseDTO;
import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @PostMapping("/")
    public void create(@Valid @RequestBody CourseEntity courseEntity) {
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseEntity>> get() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, UpdateCourseDTO updateCourseDTO) { // add a DTO with validations
        return null; // response status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return null; // response status
    }

    @PatchMapping("/course/{id}/active")
    public ResponseEntity<Object> patch(@PathVariable Long id){ // add a DTO with validations
        return null; // response status
    }
}
