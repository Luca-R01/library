package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.user.UserDtoRequest;
import com.lucarinelli.library.model.dto.user.UserDtoResponse;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
public interface UserController {

    @PostMapping
    ResponseEntity<UserDtoResponse> createUser(
            @RequestBody @Valid UserDtoRequest dtoRequest
    ) throws ConflictException;

    @GetMapping("/{id}")
    ResponseEntity<UserDtoResponse> findUserById(
            @PathVariable String id
    ) throws NotFoundException;

    @GetMapping
    ResponseEntity<List<UserDtoResponse>> findUsersByFilters(
            @ParameterObject UserDtoSearch request
    );

    @PutMapping("/{id}")
    ResponseEntity<UserDtoResponse> updateUser(
            @PathVariable String id,
            @RequestBody @Valid UserDtoRequest dtoRequest
    ) throws NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(
            @PathVariable String id
    ) throws NotFoundException;
}
