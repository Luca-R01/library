package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookDtoRequest;
import com.lucarinelli.library.model.book.BookDtoResponse;
import com.lucarinelli.library.model.book.BookDtoSearch;

import java.util.List;

public interface BookComponent {

    BookDtoResponse createBook(BookDtoRequest dtoRequest) throws ConflictException;

    BookDtoResponse findBookById(String id) throws NotFoundException;

    List<BookDtoResponse> findBooksByFilters(BookDtoSearch request);

    void updateBook(String id, BookDtoRequest dtoRequest) throws NotFoundException;

    void deleteBook(String id) throws NotFoundException;

}
