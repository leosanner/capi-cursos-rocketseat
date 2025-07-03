package com.leonardosanner.programming_courses.service.useCases.owner;

import com.leonardosanner.programming_courses.dto.owner.OwnerRegisterDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.exception.RegisterAlreadyExistsException;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class    CreateOwnerUseCase {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(OwnerRegisterDTO ownerRegisterDTO) {
//        System.out.println("Searching User...");
        ownerRepository.findByEmail(ownerRegisterDTO.getEmail()).ifPresent(
               owner -> {
//                   System.out.println("User Duplicated");
                   throw new RegisterAlreadyExistsException("Owner/User already exists.");
               }
        );

       String encodedPassword = passwordEncoder.encode(ownerRegisterDTO.getPassword());

       OwnerEntity owner = OwnerEntity.builder()
               .email(ownerRegisterDTO.getEmail())
               .password(encodedPassword)
               .name(ownerRegisterDTO.getName())
               .build();

       ownerRepository.save(owner);
    }
}
