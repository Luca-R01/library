package com.lucarinelli.library.model.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucarinelli.library.model.BookCategory;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDtoResponse {

    private String id;
    private String title;
    private String author;
    private BookCategory category;
    private Double price;
    private Integer quantity;

}
