package com.java.truth.softwares.rightshipsoftfunctions.response;

import java.time.Instant;

public record UVOceanWavesUserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Instant createdAt,
        Instant updatedAt
) {}
