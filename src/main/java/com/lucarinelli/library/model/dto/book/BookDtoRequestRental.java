package com.lucarinelli.library.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class BookDtoRequestRental {

    @NotBlank
    private String id;

    @NotNull
    private LocalDate rentalDate;

    @NotNull
    private LocalDate expirationDate;

}
