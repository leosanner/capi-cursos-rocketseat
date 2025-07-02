package com.leonardosanner.programming_courses.dto.course;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDTO {

    private String name;

    @Pattern(regexp = "^[^0-9]*$", message = "Numbers are not accepted.")
    @Length(min = 10, max = 30, message = "The category must be between (10) and (30).")
    private String category;

}
