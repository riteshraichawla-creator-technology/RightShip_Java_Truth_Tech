package com.java.truth.softwares.rightshipsoftfunctions.repository;


import com.java.truth.softwares.rightshipsoftfunctions.entities.UVOceanWavesUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UVOceanWavesUserRepository extends JpaRepository<UVOceanWavesUserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UVOceanWavesUserEntity> findByEmail(String email);

    Page<UVOceanWavesUserEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName, String lastName, String email, Pageable pageable
    );
}
