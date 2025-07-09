package com.leonardosanner.programming_courses.dto.course;

import com.leonardosanner.programming_courses.entity.course.CourseActive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseReturnDTO {

    private String name;
    private String category;
    private CourseActive status;
    private GetOwnerDTO ownerInformation;
}
