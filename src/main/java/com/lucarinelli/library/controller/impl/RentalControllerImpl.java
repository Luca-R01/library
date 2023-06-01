package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.controller.RentalController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.RentalMapper;
import com.lucarinelli.library.model.rental.RentalDtoRequest;
import com.lucarinelli.library.model.rental.RentalModel;
import com.lucarinelli.library.service.RentalService;
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

    private final RentalMapper rentalMapper;
    private final RentalService rentalService;

    @Override
    public ResponseEntity<String> createRental(@Valid RentalDtoRequest dtoRequest) throws NotFoundException, ConflictException {

        log.info("createRental - IN: {}", dtoRequest.toString());

        RentalModel rental = rentalMapper.toModel(dtoRequest);
        rentalService.createRental(dtoRequest.getUserId(), rental);
        ResponseEntity<String> response = new ResponseEntity<>("Rental created", HttpStatus.CREATED);

        log.info("createRental - OUT: {}", response.toString());
        return response;
    }
}
