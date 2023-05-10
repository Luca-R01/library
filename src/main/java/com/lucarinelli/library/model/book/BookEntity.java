package com.lucarinelli.library.model.book;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "books")
public class BookEntity {

    @Id
    private String id;
    private String title;
    private String author;
    private BookCategory category;
    private Double price;
    private Integer quantity;

}
