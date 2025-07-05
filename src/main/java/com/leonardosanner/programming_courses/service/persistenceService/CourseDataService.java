package com.leonardosanner.programming_courses.service.persistenceService;

import com.leonardosanner.programming_courses.dto.course.CreateCourseDTO;
import com.leonardosanner.programming_courses.entity.course.CourseActive;
import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.exception.OwnerNotFoundException;
import com.leonardosanner.programming_courses.exception.RegisterAlreadyExistsException;
import com.leonardosanner.programming_courses.repository.CourseRepository;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseDataService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public void createCourse(CreateCourseDTO createCourseDTO) {
        // get email (subject)
        // find owner by email -> throw exception if not find
        // verify if course already exists (for the current owner)
        // create Course Entity
        // save entity

        String ownerEmail =  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        OwnerEntity ownerEntity = this.ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(
                    () -> new OwnerNotFoundException("Owner not founded.")
                );

        Optional<CourseEntity> courseEntity = this.courseRepository.findByName(createCourseDTO.getName());
        courseEntity.ifPresent(courseEntity1 -> {
                this.verifyCourseAlreadyExists(courseEntity1, ownerEntity);
            }
        );

        CourseEntity newCourseEntity = CourseEntity
                .builder()
                .name(createCourseDTO.getName())
                .category(createCourseDTO.getCategory())
                .status(CourseActive.ONLINE)
                .owner(ownerEntity)
                .build();

        this.courseRepository.save(newCourseEntity);
        System.out.println("Course created.");
    }

    private void verifyCourseAlreadyExists(CourseEntity courseEntity, OwnerEntity ownerEntity) {
        if (courseEntity.getOwner().getId().equals(ownerEntity.getId())) {
            throw new RegisterAlreadyExistsException("The requested course already exists.");
        }
    }
}
