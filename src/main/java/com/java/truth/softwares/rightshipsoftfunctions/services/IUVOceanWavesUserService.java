package com.java.truth.softwares.rightshipsoftfunctions.services;

import com.java.truth.softwares.rightshipsoftfunctions.request.UVOceanWavesUserRequestDTO;
import com.java.truth.softwares.rightshipsoftfunctions.response.UVOceanWavesUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUVOceanWavesUserService {
    UVOceanWavesUserResponse create(UVOceanWavesUserRequestDTO request);
    UVOceanWavesUserResponse getById(Long id);
    Page<UVOceanWavesUserResponse> list(String search, Pageable pageable);
    UVOceanWavesUserResponse update(Long id, UVOceanWavesUserRequestDTO request);
    UVOceanWavesUserResponse patch(Long id, UVOceanWavesUserRequestDTO request);
    void delete(Long id);
}
