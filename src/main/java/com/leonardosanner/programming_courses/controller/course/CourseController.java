package com.leonardosanner.programming_courses.controller.course;


import com.leonardosanner.programming_courses.dto.course.UpdateCourseDTO;
import com.leonardosanner.programming_courses.entity.course.ChangeActivityCourseDTO;
import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import com.leonardosanner.programming_courses.repository.CourseRepository;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/")
    public void create(@Valid @RequestBody CourseEntity courseEntity) {
        System.out.println("Inside servlet post request...");
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseEntity>> get() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, UpdateCourseDTO updateCourseDTO) {
        return null; // response status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return null; // response status
    }

    @PatchMapping("/course/{id}/active")
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody ChangeActivityCourseDTO changeActivityCourseDTO){
        return null; // response status
    }
}
