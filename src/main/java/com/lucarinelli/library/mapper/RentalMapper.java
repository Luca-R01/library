package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.RentalDtoRequest;
import com.lucarinelli.library.model.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Component
public class RentalMapper {

    public RentalModel toModel(RentalDtoRequest dtoRequest) {
        log.info("toModel - IN: {}", dtoRequest.toString());

        Book book = Book.builder()
                .id(dtoRequest.getBookId())
                .build();

        RentalModel rentalModel = RentalModel.builder()
                .id(new ObjectId().toString())
                .book(book)
                .rentalDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        log.info("toModel - OUT: {}", rentalModel.toString());
        return rentalModel;
    }
}
