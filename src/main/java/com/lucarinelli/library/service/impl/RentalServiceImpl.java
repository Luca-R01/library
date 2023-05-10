package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.entity.Book;
import com.lucarinelli.library.model.entity.User;
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
        User user = userService.findUserById(userId);

        // Find Book
        Book book = bookService.findBookById(rentalRequest.getBook().getId());

        // Add new Rental

        // Check if Book is present in Stock
        if (book.getQuantity() == 0) {
            log.error("createRental - OUT: NotFoundException");
            throw new NotFoundException("This Book is not in stock");
        }
        book.setQuantity(book.getQuantity() - 1);

        if (user.getRentalsList() == null) {
            user.setRentalsList(List.of(rentalRequest));
        } else {
            // Check if this book already exists in rental
            Optional<RentalModel> check = user.getRentalsList().stream()
                    .filter(rental -> rental.getBook().getId().equals(rentalRequest.getBook().getId()))
                    .findFirst();

            if (check.isPresent()) {
                log.error("createRental - OUT: ConflictException");
                throw new ConflictException("This book is already");
            }
            // Save rental
            user.addRental(rentalRequest);
        }
        userService.updateUser(userId, user);
        bookService.updateBook(book.getId(), book);

        log.info("createRental - OUT: {}", rentalRequest.toString());
        return rentalRequest;
    }
}
