package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.RentalDtoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/book/rental")
public interface RentalController {

    @PostMapping("/create")
    ResponseEntity<RentalModel> createRental(
            @RequestBody @Valid RentalDtoRequest dtoRequest
    ) throws NotFoundException, ConflictException;
}
