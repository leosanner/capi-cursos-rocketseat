package com.leonardosanner.programming_courses.service.useCases.owner;

import com.leonardosanner.programming_courses.dto.owner.OwnerRegisterDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.exception.RegisterAlreadyExistsException;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOwnerUseCase {

    @Autowired
    private OwnerRepository ownerRepository;

    public void execute(OwnerRegisterDTO ownerRegisterDTO) {
       ownerRepository.findByName(ownerRegisterDTO.getName()).ifPresent(
               owner -> {
                   throw new RegisterAlreadyExistsException("Owner/User already exists.");
               }
       );

       OwnerEntity owner = OwnerEntity.builder()
               .email(ownerRegisterDTO.getEmail())
               .password(ownerRegisterDTO.getPassword())
               .name(ownerRegisterDTO.getName())
               .build();

       ownerRepository.save(owner);
    }
}
