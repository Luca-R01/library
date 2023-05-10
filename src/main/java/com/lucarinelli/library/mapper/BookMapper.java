package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.dto.book.BookDtoRequest;
import com.lucarinelli.library.model.dto.book.BookDtoResponse;
import com.lucarinelli.library.model.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookMapper {

    public Book toBook(BookDtoRequest dtoRequest) {

        log.info("toBook - IN: {}", dtoRequest.toString());

        Book book = Book.builder()
                .title(dtoRequest.getTitle())
                .category(dtoRequest.getCategory())
                .author(dtoRequest.getAuthor())
                .price(dtoRequest.getPrice())
                .quantity(dtoRequest.getQuantity())
                .build();

        log.info("toBook - OUT: {}", book.toString());
        return book;
    }

    public BookDtoResponse toDto(Book book) {

        log.info("toDto - IN: {}", book.toString());

        BookDtoResponse dtoResponse = BookDtoResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .build();

        log.info("toDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

    public List<BookDtoResponse> toDtoList(List<Book> booksList) {

        log.info("toDtoList - IN: {}", booksList.toString());

        List<BookDtoResponse> dtoResponseList = booksList.stream()
                .map(book -> toDto(book))
                .collect(Collectors.toList());

        log.info("toDtoList - OUT: {}", dtoResponseList.toString());
        return dtoResponseList;
    }

}
