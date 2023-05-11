package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserPageDtoResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<UserPageDtoResponse> findUsersByFilters(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @ParameterObject UserDtoSearch request
    );

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(
            @PathVariable String id,
            @RequestBody @Valid UserDtoRequest dtoRequest
    ) throws NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(
            @PathVariable String id
    ) throws NotFoundException;
}
