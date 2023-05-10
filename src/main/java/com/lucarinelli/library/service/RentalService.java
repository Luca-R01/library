package com.lucarinelli.library.service;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.RentalModel;

public interface RentalService {

    RentalModel createRental(String id, RentalModel rentalModel) throws NotFoundException, ConflictException;

}
