package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.RentalDtoRequest;

public interface RentalComponent {

    RentalModel createRental(RentalDtoRequest dtoRequest) throws NotFoundException, ConflictException;

}
