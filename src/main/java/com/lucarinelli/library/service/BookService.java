package com.lucarinelli.library.service;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookEntity;
import org.springframework.data.domain.Page;

public interface BookService {

    BookEntity createBook(BookEntity newBookEntity) throws ConflictException;

    BookEntity findBookById(String id) throws NotFoundException;

    Page<BookEntity> findBooksByFilters(Integer page, Integer pageSize, BookDtoSearch request);

    BookEntity updateBook(String id, BookEntity newBookEntity) throws NotFoundException;

    void deleteBook(String id) throws NotFoundException;

}
