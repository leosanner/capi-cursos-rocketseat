package com.leonardosanner.programming_courses.entity.owner;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "owner")
public class OwnerEntity {

    private UUID id;

    @Pattern(regexp = "^[^0-9]*$", message = "Numbers are not accepted.")
    @Length(max=50, message = "The name must contain at most (50) characters.")
    @NotBlank
    private String name;

    @Email(message = "Invalid email received.")
    @NotBlank
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
