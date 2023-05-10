package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.rental.RentalDtoRequest;
import com.lucarinelli.library.model.rental.RentalModel;

public interface RentalComponent {

    RentalModel createRental(RentalDtoRequest dtoRequest) throws NotFoundException, ConflictException;

}
