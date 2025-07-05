package com.leonardosanner.programming_courses.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDTO {

    @NotBlank(message = "You should send a name for the course.")
    private String name;

    @NotBlank(message = "Every course should have a category related.")
    private String category;
}
