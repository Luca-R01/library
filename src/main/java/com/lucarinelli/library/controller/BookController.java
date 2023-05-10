package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.book.BookDtoRequest;
import com.lucarinelli.library.model.dto.book.BookDtoResponse;
import com.lucarinelli.library.model.dto.book.BookDtoSearch;
import com.lucarinelli.library.model.entity.Book;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/book")
public interface BookController {

    @PostMapping
    ResponseEntity<BookDtoResponse> createBook(
            @RequestBody @Valid BookDtoRequest dtoRequest
    ) throws ConflictException;

    @GetMapping("/{id}")
    ResponseEntity<BookDtoResponse> findBookById(
            @PathVariable String id
    ) throws NotFoundException;

    @GetMapping
    ResponseEntity<List<BookDtoResponse>> findBooksByFilters(
            @ParameterObject BookDtoSearch request
    );

    @PutMapping("/{id}")
    ResponseEntity<BookDtoResponse> updateBook(
            @PathVariable String id,
            @RequestBody @Valid BookDtoRequest dtoRequest
    ) throws NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(
            @PathVariable String id
    ) throws NotFoundException;
}
