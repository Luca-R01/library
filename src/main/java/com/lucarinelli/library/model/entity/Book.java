package com.lucarinelli.library.model.entity;

import com.lucarinelli.library.model.BookCategory;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private BookCategory category;
    private Double price;
    private Integer quantity;

}
