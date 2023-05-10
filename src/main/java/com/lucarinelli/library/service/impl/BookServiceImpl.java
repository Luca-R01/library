package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.book.BookDtoSearch;
import com.lucarinelli.library.model.entity.Book;
import com.lucarinelli.library.repository.BookRepository;
import com.lucarinelli.library.repository.manager.BookRepositoryManager;
import com.lucarinelli.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRepositoryManager bookRepositoryManager;

    @Override
    public Book createBook(Book newBook) throws ConflictException {
        log.info("createBook - IN: {}", newBook.toString());

        // Check if Book already exists
        BookDtoSearch request = BookDtoSearch.builder()
                .title(newBook.getTitle())
                .author(newBook.getAuthor())
                .build();

        List<Book> book = findBooksByFilters(request);
        if (!book.isEmpty()) {
            log.error("createBook - OUT: ConflictException");
            throw new ConflictException("The Book alredy exists!");
        }
        // Insert Book
        newBook = bookRepository.save(newBook);

        log.info("createBook - OUT: {}", newBook.toString());
        return newBook;
    }

    @Override
    public Book findBookById(String id) throws NotFoundException {
        log.info("findBookById - OUT: id({})", id);

        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()){
            log.error("findBookById - OUT: NotFoundException");
            throw new NotFoundException("Book not found");
        }

        log.info("findBookById - OUT: {}", book.get().toString());
        return book.get();
    }

    @Override
    public List<Book> findBooksByFilters(BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        List<Book> books = bookRepositoryManager.findBooksByFilters(request);

        log.info("findByBooksByFilter - OUT: {}", books.toString());
        return books;
    }

    @Override
    public Book updateBook(String id, Book newBook) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, newBook.toString());

        // Check if Book with input id exists
        Book oldBook = findBookById(id);
        // Update Book
        newBook.setId(oldBook.getId());
        bookRepository.save(newBook);

        log.info("updateBook - OUT: {}", newBook.toString());
        return newBook;
    }

    @Override
    public void deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        // Check if Book with input id exists
        Book book = findBookById(id);
        // Delete Book
        bookRepository.delete(book);

        log.info("deleteBook - OUT: END");
    }

}
