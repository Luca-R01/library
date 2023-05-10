package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.book.BookEntity;
import com.lucarinelli.library.model.rental.RentalDtoRequest;
import com.lucarinelli.library.model.rental.RentalModel;
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

        BookEntity bookEntity = BookEntity.builder()
                .id(dtoRequest.getBookId())
                .build();

        RentalModel rentalModel = RentalModel.builder()
                .id(new ObjectId().toString())
                .bookEntity(bookEntity)
                .rentalDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        log.info("toModel - OUT: {}", rentalModel.toString());
        return rentalModel;
    }
}
