package com.lucarinelli.library.model.dto.book;

import com.lucarinelli.library.model.BookCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDtoRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private BookCategory category;

    @NotNull
    @Min(value = 0)
    private Double price;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

}
