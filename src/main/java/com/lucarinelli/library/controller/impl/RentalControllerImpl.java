package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.component.RentalComponent;
import com.lucarinelli.library.controller.RentalController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.RentalDtoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RentalControllerImpl implements RentalController {

    private final RentalComponent rentalComponent;

    @Override
    public ResponseEntity<RentalModel> createRental(@Valid RentalDtoRequest dtoRequest) throws NotFoundException, ConflictException {

        log.info("createRental - IN: {}", dtoRequest.toString());

        RentalModel rentalResponse = rentalComponent.createRental(dtoRequest);
        ResponseEntity<RentalModel> response = new ResponseEntity<>(rentalResponse, HttpStatus.CREATED);

        log.info("createRental - OUT: {}", response.toString());
        return response;
    }
}
