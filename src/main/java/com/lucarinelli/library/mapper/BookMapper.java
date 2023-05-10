package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.book.BookDtoRequest;
import com.lucarinelli.library.model.book.BookDtoResponse;
import com.lucarinelli.library.model.book.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookMapper {

    public BookEntity toBook(BookDtoRequest dtoRequest) {
        log.info("toBook - IN: {}", dtoRequest.toString());

        BookEntity bookEntity = BookEntity.builder()
                .title(dtoRequest.getTitle())
                .category(dtoRequest.getCategory())
                .author(dtoRequest.getAuthor())
                .price(dtoRequest.getPrice())
                .quantity(dtoRequest.getQuantity())
                .build();

        log.info("toBook - OUT: {}", bookEntity.toString());
        return bookEntity;
    }

    public BookDtoResponse toDto(BookEntity bookEntity) {
        log.info("toDto - IN: {}", bookEntity.toString());

        BookDtoResponse dtoResponse = BookDtoResponse.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .category(bookEntity.getCategory())
                .price(bookEntity.getPrice())
                .quantity(bookEntity.getQuantity())
                .build();

        log.info("toDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

    public List<BookDtoResponse> toDtoList(List<BookEntity> booksList) {
        log.info("toDtoList - IN: {}", booksList.toString());

        List<BookDtoResponse> dtoResponseList = booksList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        log.info("toDtoList - OUT: {}", dtoResponseList.toString());
        return dtoResponseList;
    }

    public BookDtoResponse toIdDto(BookEntity bookEntity) {
        log.info("toIdDto - IN: {}", bookEntity.toString());

        BookDtoResponse dtoResponse = BookDtoResponse.builder()
                .id(bookEntity.getId())
                .build();

        log.info("toIdDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

}
