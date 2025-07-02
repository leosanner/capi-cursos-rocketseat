package com.leonardosanner.programming_courses.entity.course;


import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @Pattern(regexp = "^[^0-9]*$", message = "Numbers are not accepted.")
    @Length(min = 10, max = 30, message = "The category must be between (10) and (30).")
    @NotBlank
    private String category;

    @Enumerated(EnumType.STRING)
    private CourseActive status;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
