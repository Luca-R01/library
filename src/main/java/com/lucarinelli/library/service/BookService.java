package com.lucarinelli.library.service;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.book.BookDtoSearch;
import com.lucarinelli.library.model.entity.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book newBook) throws ConflictException;

    Book findBookById(String id) throws NotFoundException;

    List<Book> findBooksByFilters(BookDtoSearch request);

    Book updateBook(String id, Book newBook) throws NotFoundException;

    void deleteBook(String id) throws NotFoundException;

}
