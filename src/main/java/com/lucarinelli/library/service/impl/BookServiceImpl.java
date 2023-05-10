package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookEntity;
import com.lucarinelli.library.repository.BookRepository;
import com.lucarinelli.library.repository.BookRepositoryManager;
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
    public BookEntity createBook(BookEntity newBookEntity) throws ConflictException {
        log.info("createBook - IN: {}", newBookEntity.toString());

        // Check if Book already exists
        BookDtoSearch request = BookDtoSearch.builder()
                .title(newBookEntity.getTitle())
                .author(newBookEntity.getAuthor())
                .build();

        List<BookEntity> bookEntity = findBooksByFilters(request);
        if (!bookEntity.isEmpty()) {
            log.error("createBook - OUT: ConflictException");
            throw new ConflictException("The Book alredy exists!");
        }
        // Insert Book
        newBookEntity = bookRepository.save(newBookEntity);

        log.info("createBook - OUT: {}", newBookEntity.toString());
        return newBookEntity;
    }

    @Override
    public BookEntity findBookById(String id) throws NotFoundException {
        log.info("findBookById - OUT: id({})", id);

        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            log.error("findBookById - OUT: NotFoundException");
            throw new NotFoundException("Book not found");
        }

        log.info("findBookById - OUT: {}", book.get().toString());
        return book.get();
    }

    @Override
    public List<BookEntity> findBooksByFilters(BookDtoSearch request) {
        log.info("findByBooksByFilter - IN: {}", request.toString());

        List<BookEntity> bookEntities = bookRepositoryManager.findBooksByFilters(request);

        log.info("findByBooksByFilter - OUT: {}", bookEntities.toString());
        return bookEntities;
    }

    @Override
    public BookEntity updateBook(String id, BookEntity newBookEntity) throws NotFoundException {
        log.info("updateBook - IN: id({}), {}", id, newBookEntity.toString());

        // Check if Book with input id exists
        BookEntity oldBookEntity = findBookById(id);
        // Update Book
        newBookEntity.setId(oldBookEntity.getId());
        bookRepository.save(newBookEntity);

        log.info("updateBook - OUT: {}", newBookEntity.toString());
        return newBookEntity;
    }

    @Override
    public void deleteBook(String id) throws NotFoundException {
        log.info("deleteBook - IN: id({})", id);

        // Check if Book with input id exists
        BookEntity bookEntity = findBookById(id);
        // Delete Book
        bookRepository.delete(bookEntity);

        log.info("deleteBook - OUT: END");
    }

}
