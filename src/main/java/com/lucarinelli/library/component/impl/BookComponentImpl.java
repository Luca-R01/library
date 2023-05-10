package com.lucarinelli.library.component.impl;

import com.lucarinelli.library.component.BookComponent;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.BookMapper;
import com.lucarinelli.library.model.dto.book.BookDtoRequest;
import com.lucarinelli.library.model.dto.book.BookDtoResponse;
import com.lucarinelli.library.model.dto.book.BookDtoSearch;
import com.lucarinelli.library.model.entity.Book;
import com.lucarinelli.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookComponentImpl implements BookComponent {

    private final BookMapper bookMapper;

    private final BookService bookService;

    @Override
    public BookDtoResponse createBook(BookDtoRequest dtoRequest) throws ConflictException {
        log.info("createBook - IN: {}", dtoRequest.toString());

        Book bookRequest = bookMapper.toBook(dtoRequest);
        Book book = bookService.createBook(bookRequest);
        BookDtoResponse response = bookMapper.toDto(book);

        log.info("createBook - OUT: {}", response);
        return response;
    }

    @Override
    public BookDtoResponse findBookById(String id) throws NotFoundException {
        log.info("findBookById - IN: id({})", id);

        Book book = bookService.findBookById(id);
        BookDtoResponse response = bookMapper.toDto(book);

        log.info("findBookById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public List<BookDtoResponse> findBooksByFilters(BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        List<Book> books = bookService.findBooksByFilters(request);
        List<BookDtoResponse> response = bookMapper.toDtoList(books);

        log.info("findByBooksByFilter - OUT: {}", response);
        return response;
    }

    @Override
    public BookDtoResponse updateBook(String id, BookDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, dtoRequest.toString());

        Book bookRequest = bookMapper.toBook(dtoRequest);
        Book book = bookService.updateBook(id, bookRequest);
        BookDtoResponse response = bookMapper.toDto(book);

        log.info("updateBook - OUT: {}", response);
        return response;
    }

    @Override
    public void deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        bookService.deleteBook(id);

        log.info("deleteBook - OUT: END");
    }
}
