package com.lucarinelli.library.component.impl;

import com.lucarinelli.library.component.BookComponent;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.BookMapper;
import com.lucarinelli.library.model.book.*;
import com.lucarinelli.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookComponentImpl implements BookComponent {

    private final BookMapper bookMapper;

    private final BookService bookService;

    @Override
    public BookDtoResponse createBook(BookDtoRequest dtoRequest) throws ConflictException {
        log.info("createBook - IN: {}", dtoRequest.toString());

        BookEntity bookEntityRequest = bookMapper.toBook(dtoRequest);
        BookEntity bookEntity = bookService.createBook(bookEntityRequest);
        BookDtoResponse response = bookMapper.toIdDto(bookEntity);

        log.info("createBook - OUT: {}", response);
        return response;
    }

    @Override
    public BookDtoResponse findBookById(String id) throws NotFoundException {
        log.info("findBookById - IN: id({})", id);

        BookEntity bookEntity = bookService.findBookById(id);
        BookDtoResponse response = bookMapper.toDto(bookEntity);

        log.info("findBookById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public BookPageDtoResponse findBooksByFilters(Integer page, Integer pageSize, BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        Page<BookEntity> bookEntities = bookService.findBooksByFilters(page, pageSize, request);
        BookPageDtoResponse response = bookMapper.toPageDto(bookEntities);

        log.info("findByBooksByFilter - OUT: {}", response);
        return response;
    }

    @Override
    public void updateBook(String id, BookDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, dtoRequest.toString());

        BookEntity bookEntityRequest = bookMapper.toBook(dtoRequest);
        bookService.updateBook(id, bookEntityRequest);

        log.info("updateBook - OUT: End method");
    }

    @Override
    public void deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        bookService.deleteBook(id);

        log.info("deleteBook - OUT: END");
    }
}
