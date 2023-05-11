package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.component.BookComponent;
import com.lucarinelli.library.controller.BookController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookDtoRequest;
import com.lucarinelli.library.model.book.BookDtoResponse;
import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookPageDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookControllerImpl implements BookController {

    private final BookComponent bookComponent;

    @Override
    public ResponseEntity<BookDtoResponse> createBook(@Valid BookDtoRequest dtoRequest) throws ConflictException {
        log.info("createBook - IN: {}", dtoRequest.toString());

        BookDtoResponse bookResponse = bookComponent.createBook(dtoRequest);
        ResponseEntity<BookDtoResponse> response = new ResponseEntity<>(bookResponse, HttpStatus.CREATED);

        log.info("createBook - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<BookDtoResponse> findBookById(String id) throws NotFoundException {
        log.info("findBookById - IN: id({})", id);

        BookDtoResponse bookResponse = bookComponent.findBookById(id);
        ResponseEntity<BookDtoResponse> response = new ResponseEntity<>(bookResponse, HttpStatus.OK);

        log.info("findBookById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<BookPageDtoResponse> findBooksByFilters(Integer page, Integer pageSize, BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        BookPageDtoResponse bookResponse = bookComponent.findBooksByFilters(page, pageSize, request);
        ResponseEntity<BookPageDtoResponse> response = new ResponseEntity<>(bookResponse, HttpStatus.OK);

        log.info("findByBooksByFilter - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> updateBook(String id, BookDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, dtoRequest.toString());

        bookComponent.updateBook(id, dtoRequest);
        ResponseEntity<String> response = new ResponseEntity<>("Book updated", HttpStatus.OK);

        log.info("updateBook - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        bookComponent.deleteBook(id);
        ResponseEntity<String> response = new ResponseEntity<>("Book deleted", HttpStatus.OK);

        log.info("deleteBook - OUT: {}", response.toString());
        return response;
    }

}
