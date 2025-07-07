package com.leonardosanner.programming_courses.controller.course;


import com.leonardosanner.programming_courses.dto.course.CreateCourseDTO;
import com.leonardosanner.programming_courses.dto.course.SearchCourseDTO;
import com.leonardosanner.programming_courses.dto.course.UpdateCourseDTO;
import com.leonardosanner.programming_courses.entity.course.ChangeActivityCourseDTO;
import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import com.leonardosanner.programming_courses.repository.CourseRepository;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import com.leonardosanner.programming_courses.service.persistenceService.CourseDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDataService courseDataService;

    @PostMapping("/")
    public void create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        this.courseDataService.createCourse(createCourseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseEntity>> get(@RequestBody SearchCourseDTO searchCourseDTO) {
        return ResponseEntity.ok().body(courseDataService.getCourses(searchCourseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UpdateCourseDTO updateCourseDTO) {
        System.out.println(id);
        this.courseDataService.editCourseById(id, updateCourseDTO);
        return ResponseEntity.ok().body("Course edited."); // response status
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
