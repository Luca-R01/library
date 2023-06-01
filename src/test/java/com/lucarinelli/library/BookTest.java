package com.lucarinelli.library;

import com.lucarinelli.library.controller.BookController;
import com.lucarinelli.library.controller.impl.BookControllerImpl;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.BookMapper;
import com.lucarinelli.library.model.book.BookDtoRequest;
import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookEntity;
import com.lucarinelli.library.repository.BookRepository;
import com.lucarinelli.library.repository.BookRepositoryManager;
import com.lucarinelli.library.service.BookService;
import com.lucarinelli.library.service.impl.BookServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookRepositoryManager bookRepositoryManager;

    private BookController bookController;

    private EasyRandom generator;

    @BeforeEach
    void setup() {
        // Inject dependencies
        BookService bookService = new BookServiceImpl(bookRepository, bookRepositoryManager);
        BookMapper bookMapper = new BookMapper();
        bookController = new BookControllerImpl(bookMapper, bookService);

        generator = new EasyRandom();
    }

    @Test
    void createBook() throws ConflictException {
        BookEntity book = generator.nextObject(BookEntity.class);
        BookDtoRequest bookDtoRequest = generator.nextObject(BookDtoRequest.class);

        when(bookRepositoryManager.findBooksByFilters(any(Pageable.class), any(BookDtoSearch.class))).thenReturn(Page.empty());
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);
        assertNotNull(bookController.createBook(bookDtoRequest));
    }

    @Test
    void findBooksByFilters() {
        List<BookEntity> bookList = generator.objects(BookEntity.class, 3).toList();
        Pageable pageable = PageRequest.of(0, 10);
        Page<BookEntity> bookPage = new PageImpl<>(bookList, pageable, 3);
        BookDtoSearch bookDtoSearch = BookDtoSearch.builder().build();

        when(bookRepositoryManager.findBooksByFilters(any(Pageable.class), any(BookDtoSearch.class))).thenReturn(bookPage);
        assertNotNull(bookController.findBooksByFilters(0, 10, bookDtoSearch));
    }

    @Test
    void findBookById() throws NotFoundException {
        BookEntity book = generator.nextObject(BookEntity.class);

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        assertNotNull(bookController.findBookById("id"));
    }

    @Test
    void deleteBook() throws NotFoundException {
        BookEntity book = generator.nextObject(BookEntity.class);

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        assertNotNull(bookController.deleteBook("id"));
    }

}
