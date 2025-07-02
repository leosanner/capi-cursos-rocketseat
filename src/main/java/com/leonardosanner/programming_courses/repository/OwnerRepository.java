package com.leonardosanner.programming_courses.repository;

import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    Optional<OwnerEntity> findById(Long id);
    Optional<OwnerEntity> findByName(String name);
}
