package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.controller.BookController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.BookMapper;
import com.lucarinelli.library.model.book.*;
import com.lucarinelli.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookControllerImpl implements BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @Override
    public ResponseEntity<BookDtoResponse> createBook(@Valid BookDtoRequest dtoRequest) throws ConflictException {
        log.info("createBook - IN: {}", dtoRequest.toString());

        BookEntity book = bookMapper.toBook(dtoRequest);
        book = bookService.createBook(book);
        BookDtoResponse bookDtoResponse = bookMapper.toIdDto(book);
        ResponseEntity<BookDtoResponse> response = new ResponseEntity<>(bookDtoResponse, HttpStatus.CREATED);

        log.info("createBook - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<BookDtoResponse> findBookById(String id) throws NotFoundException {
        log.info("findBookById - IN: id({})", id);

        BookEntity book = bookService.findBookById(id);
        BookDtoResponse bookDtoResponse = bookMapper.toDto(book);
        ResponseEntity<BookDtoResponse> response = new ResponseEntity<>(bookDtoResponse, HttpStatus.OK);

        log.info("findBookById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<BookPageDtoResponse> findBooksByFilters(Integer page, Integer pageSize, BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        Page<BookEntity> bookList = bookService.findBooksByFilters(page, pageSize, request);
        BookPageDtoResponse bookPage = bookMapper.toPageDto(bookList);
        ResponseEntity<BookPageDtoResponse> response = new ResponseEntity<>(bookPage, HttpStatus.OK);

        log.info("findByBooksByFilter - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> updateBook(String id, BookDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, dtoRequest.toString());

        BookEntity bookRequest = bookMapper.toBook(dtoRequest);
        bookService.updateBook(id, bookRequest);
        ResponseEntity<String> response = new ResponseEntity<>("Book updated", HttpStatus.OK);

        log.info("updateBook - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        bookService.deleteBook(id);
        ResponseEntity<String> response = new ResponseEntity<>("Book deleted", HttpStatus.OK);

        log.info("deleteBook - OUT: {}", response.toString());
        return response;
    }

}
