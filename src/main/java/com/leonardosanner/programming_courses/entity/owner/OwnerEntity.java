package com.leonardosanner.programming_courses.entity.owner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[^0-9]*$", message = "Numbers are not accepted.")
    @Length(max=50, message = "The name must contain at most (50) characters.")
    @NotBlank(message = "This field should not be blank.")
    private String name;

    @NotBlank(message = "This field should not be blank")
    @Length(max=50, message = "The name must contain at most (50) characters.")
    private String password;

    @Email(message = "Invalid email received.")
    @NotBlank(message = "This field should not be blank")
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
