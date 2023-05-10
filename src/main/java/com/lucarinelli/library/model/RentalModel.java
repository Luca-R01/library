package com.lucarinelli.library.model;

import com.lucarinelli.library.model.entity.Book;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Builder
@Data
public class RentalModel {

    @Id
    private String id;

    @DBRef
    private Book book;

    private LocalDate rentalDate;

    private LocalDate expirationDate;

}
