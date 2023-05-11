package com.lucarinelli.library.controller;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookDtoRequest;
import com.lucarinelli.library.model.book.BookDtoResponse;
import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookPageDtoResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<BookPageDtoResponse> findBooksByFilters(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @ParameterObject BookDtoSearch request
    );

    @PutMapping("/{id}")
    ResponseEntity<String> updateBook(
            @PathVariable String id,
            @RequestBody @Valid BookDtoRequest dtoRequest
    ) throws NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(
            @PathVariable String id
    ) throws NotFoundException;
}
