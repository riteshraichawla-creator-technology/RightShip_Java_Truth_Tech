package com.java.truth.softwares.rightshipsoftfunctions.controller;

import com.java.truth.softwares.rightshipsoftfunctions.request.UVOceanWavesUserRequestDTO;
import com.java.truth.softwares.rightshipsoftfunctions.response.UVOceanWavesUserResponse;
import com.java.truth.softwares.rightshipsoftfunctions.services.IUVOceanWavesUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UVOceanWavesUserController {

    private final IUVOceanWavesUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UVOceanWavesUserResponse create(@Valid @RequestBody UVOceanWavesUserRequestDTO request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public UVOceanWavesUserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<UVOceanWavesUserResponse> list(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "lastName") Pageable pageable
    ) {
        return userService.list(search, pageable);
    }

    @PutMapping("/{id}")
    public UVOceanWavesUserResponse update(@PathVariable Long id, @Valid @RequestBody UVOceanWavesUserRequestDTO request) {
        return userService.update(id, request);
    }

    @PatchMapping("/{id}")
    public UVOceanWavesUserResponse patch(@PathVariable Long id, @RequestBody UVOceanWavesUserRequestDTO request) {
        // Partial update (validation handled in service)
        return userService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
