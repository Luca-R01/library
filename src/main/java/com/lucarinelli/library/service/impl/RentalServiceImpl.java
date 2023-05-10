package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookEntity;
import com.lucarinelli.library.model.rental.RentalModel;
import com.lucarinelli.library.model.user.UserEntity;
import com.lucarinelli.library.service.BookService;
import com.lucarinelli.library.service.RentalService;
import com.lucarinelli.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {

    private final BookService bookService;
    private final UserService userService;

    @Override
    public RentalModel createRental(String userId, RentalModel rentalRequest) throws NotFoundException, ConflictException {
        log.info("createRental - IN: userId({}), {}", userId, rentalRequest.toString());


        // Find User
        UserEntity userEntity = userService.findUserById(userId);

        // Find Book
        BookEntity bookEntity = bookService.findBookById(rentalRequest.getBookEntity().getId());

        // Add new Rental

        // Check if Book is present in Stock
        if (bookEntity.getQuantity() == 0) {
            log.error("createRental - OUT: NotFoundException");
            throw new NotFoundException("This Book is not in stock");
        }
        bookEntity.setQuantity(bookEntity.getQuantity() - 1);

        if (userEntity.getRentalsList() == null) {
            userEntity.setRentalsList(List.of(rentalRequest));
        } else {
            // Check if this book already exists in rental
            Optional<RentalModel> check = userEntity.getRentalsList().stream()
                    .filter(rental -> rental.getBookEntity().getId().equals(rentalRequest.getBookEntity().getId()))
                    .findFirst();

            if (check.isPresent()) {
                log.error("createRental - OUT: ConflictException");
                throw new ConflictException("This book is already");
            }
            // Save rental
            userEntity.addRental(rentalRequest);
        }
        userService.updateUser(userId, userEntity);
        bookService.updateBook(bookEntity.getId(), bookEntity);

        log.info("createRental - OUT: {}", rentalRequest.toString());
        return rentalRequest;
    }
}
