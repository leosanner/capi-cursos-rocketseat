package com.leonardosanner.programming_courses.service.persistenceService;

import com.leonardosanner.programming_courses.dto.course.CreateCourseDTO;
import com.leonardosanner.programming_courses.dto.course.SearchCourseDTO;
import com.leonardosanner.programming_courses.dto.course.UpdateCourseDTO;
import com.leonardosanner.programming_courses.entity.course.CourseActive;
import com.leonardosanner.programming_courses.entity.course.CourseEntity;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.exception.OwnerNotFoundException;
import com.leonardosanner.programming_courses.exception.RegisterAlreadyExistsException;
import com.leonardosanner.programming_courses.repository.CourseRepository;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

@Service
public class CourseDataService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public ResponseEntity<Object> createCourse(CreateCourseDTO createCourseDTO) {
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

        return ResponseEntity.ok().body("Course created with success.");
    }

    public List<CourseEntity> getCourses(SearchCourseDTO searchCourseDTO) {
        // verify witch field has been informed and search in the repository by the field
        // 2 options -> name, category
        // any user in the future can access this resource

        if (searchCourseDTO.getName() != null && searchCourseDTO.getCategory()!=null) {
            return this.courseRepository.findByNameAndCategory(
                    searchCourseDTO.getName(), searchCourseDTO.getCategory()
            );
        }

        if (searchCourseDTO.getName() != null && !searchCourseDTO.getName().isBlank()) {
             var entity = this.courseRepository.findByName(searchCourseDTO.getName());

             if (entity.isPresent()) {
                 return Arrays.asList(entity.get());
             } else {
                 return null;
             }
        }

        if (searchCourseDTO.getCategory() != null && !searchCourseDTO.getCategory().isBlank()) {
            List<CourseEntity> listEntities = this.courseRepository
                    .findByCategory(searchCourseDTO.getCategory());

            return listEntities;
        }
        return null;
    }


    public void editCourseById(Long id, UpdateCourseDTO updateCourseDTO) {
        // Verify if a course with the informed id exists
        // Get the owner email via JWT principal
        // Compare if the owner JWT email equals the email of the owner course
        // If true -> change with the data from updateCourseDTO

        CourseEntity courseEntity = this.checkRequest(id);

        String newName = updateCourseDTO.getName();
        String newCategory = updateCourseDTO.getCategory();

        if (!newName.isBlank()) {
            courseEntity.setName(newName);
        }

        if (!newCategory.isBlank()) {
            courseEntity.setCategory(newCategory);
        }

        if (!newName.isBlank() || !newCategory.isBlank()) {
            this.courseRepository.save(courseEntity);
        }
    }

    public void deleteCourseById(Long id) {
        CourseEntity courseEntity = this.checkRequest(id);

        this.courseRepository.delete(courseEntity);
    }

    public void changeCourseActiveStatus(Long id){
        CourseEntity courseEntity = this.checkRequest(id);

        if (courseEntity.getStatus().getActiveStatus()) {
            courseEntity.setStatus(CourseActive.OFFLINE);
        } else {
            courseEntity.setStatus(CourseActive.ONLINE);
        }

        this.courseRepository.save(courseEntity);
    }

    private void verifyCourseAlreadyExists(CourseEntity courseEntity, OwnerEntity ownerEntity) {
        if (courseEntity.getOwner().getId().equals(ownerEntity.getId())) {
            throw new RegisterAlreadyExistsException("The requested course already exists.");
        }
    }

    private CourseEntity checkRequest(Long id) {
        // verify if the course exists and if the JWT principal (email information exists)

        CourseEntity courseEntity = this.courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not founded")
        );

        String ownerEmailJWT = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();


        if (!courseEntity.getOwner().getEmail().equals(ownerEmailJWT)) {
            throw new RuntimeException("Only the owner is allowed to delete this course");
        }

        return courseEntity;
    }
}
