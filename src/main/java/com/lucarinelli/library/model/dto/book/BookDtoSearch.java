package com.lucarinelli.library.model.dto.book;

import com.lucarinelli.library.model.BookCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDtoSearch {

    private String title;
    private String author;
    private BookCategory category;
    private Double price;
    private Integer quantity;

}
