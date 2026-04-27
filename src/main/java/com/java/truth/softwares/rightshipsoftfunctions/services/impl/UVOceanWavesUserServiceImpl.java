package com.java.truth.softwares.rightshipsoftfunctions.services.impl;

import com.java.truth.softwares.rightshipsoftfunctions.entities.UVOceanWavesUserEntity;
import com.java.truth.softwares.rightshipsoftfunctions.exception.DuplicateEmailException;
import com.java.truth.softwares.rightshipsoftfunctions.exception.ResourceNotFoundException;
import com.java.truth.softwares.rightshipsoftfunctions.helpers.UVOceanWavesEmailSanitizer;
import com.java.truth.softwares.rightshipsoftfunctions.repository.UVOceanWavesUserRepository;
import com.java.truth.softwares.rightshipsoftfunctions.request.UVOceanWavesUserRequestDTO;
import com.java.truth.softwares.rightshipsoftfunctions.response.UVOceanWavesUserResponse;
import com.java.truth.softwares.rightshipsoftfunctions.services.IUVOceanWavesUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UVOceanWavesUserServiceImpl implements IUVOceanWavesUserService {

    private final UVOceanWavesUserRepository userRepository;
    private final UVOceanWavesEmailSanitizer emailSanitizer; // custom Spring bean

    @Override
    public UVOceanWavesUserResponse create(UVOceanWavesUserRequestDTO request) {
        String email = emailSanitizer.sanitize(request.email());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already in use: " + email);
        }
        UVOceanWavesUserEntity rightDigitsUserEntity = UVOceanWavesUserEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(email)
                .phone(request.phone())
                .build();
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public UVOceanWavesUserResponse getById(Long id) {
        UVOceanWavesUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UVOceanWavesUser not found: id=" + id));
        return toResponse(rightDigitsUserEntity);
    }

    @Override
    public Page<UVOceanWavesUserResponse> list(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return userRepository.findAll(pageable).map(this::toResponse);
        }
        return userRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        search, search, search, pageable
                ).map(this::toResponse);
    }

    @Override
    public UVOceanWavesUserResponse update(Long id, UVOceanWavesUserRequestDTO request) {
        UVOceanWavesUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UVOceanWavesUser not found: id=" + id));

        String newEmail = emailSanitizer.sanitize(request.email());
        if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw new DuplicateEmailException("Email already in use: " + newEmail);
        }

        rightDigitsUserEntity.setFirstName(request.firstName());
        rightDigitsUserEntity.setLastName(request.lastName());
        rightDigitsUserEntity.setEmail(newEmail);
        rightDigitsUserEntity.setPhone(request.phone());

        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public UVOceanWavesUserResponse patch(Long id, UVOceanWavesUserRequestDTO request) {
        UVOceanWavesUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UVOceanWavesUser not found: id=" + id));

        if (request.firstName() != null && !request.firstName().isBlank()) {
            rightDigitsUserEntity.setFirstName(request.firstName());
        }
        if (request.lastName() != null && !request.lastName().isBlank()) {
            rightDigitsUserEntity.setLastName(request.lastName());
        }
        if (request.email() != null && !request.email().isBlank()) {
            String newEmail = emailSanitizer.sanitize(request.email());
            if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
                throw new DuplicateEmailException("Email already in use: " + newEmail);
            }
            rightDigitsUserEntity.setEmail(newEmail);
        }
        if (request.phone() != null) {
            rightDigitsUserEntity.setPhone(request.phone());
        }
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("UVOceanWavesUser not found: id=" + id);
        }
        userRepository.deleteById(id);
    }

    private UVOceanWavesUserResponse toResponse(UVOceanWavesUserEntity u) {
        return new UVOceanWavesUserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getPhone(),
                u.getCreatedAt(),
                u.getUpdatedAt()
        );
    }
}
