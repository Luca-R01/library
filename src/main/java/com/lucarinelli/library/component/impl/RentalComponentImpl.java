package com.lucarinelli.library.component.impl;

import com.lucarinelli.library.component.RentalComponent;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.RentalMapper;
import com.lucarinelli.library.model.rental.RentalDtoRequest;
import com.lucarinelli.library.model.rental.RentalModel;
import com.lucarinelli.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RentalComponentImpl implements RentalComponent {

    private final RentalMapper rentalMapper;

    private final RentalService rentalService;

    @Override
    public RentalModel createRental(RentalDtoRequest dtoRequest) throws NotFoundException, ConflictException {

        log.info("createRental - IN: {}", dtoRequest.toString());

        RentalModel rentalModel = rentalMapper.toModel(dtoRequest);
        rentalModel = rentalService.createRental(dtoRequest.getUserId(), rentalModel);

        log.info("createRental - OUT: {}", rentalModel.toString());
        return rentalModel;
    }
}
