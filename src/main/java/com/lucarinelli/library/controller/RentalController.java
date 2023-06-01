package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.rental.RentalDtoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/rental")
public interface RentalController {

    @PostMapping
    ResponseEntity<String> createRental(
            @RequestBody @Valid RentalDtoRequest dtoRequest
    ) throws NotFoundException, ConflictException;
}
