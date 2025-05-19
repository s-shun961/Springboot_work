package com.college.yi.bookmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.model.BookEntity;
import com.college.yi.bookmanager.repository.BookRepository;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookEntity bookEntity;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("Java超入門");
        bookEntity.setAuthor("山田 太郎");
        bookEntity.setPublisher("技術評論社");
        bookEntity.setPublishedDate(LocalDate.of(2023,9,1));
        bookEntity.setStock(10);

        book = new Book();
        book.setTitle("Java超入門");
        book.setAuthor("山田 太郎");
        book.setPublisher("技術評論社");
        book.setPublishedDate(LocalDate.of(2023,9,1));
        book.setStock(10);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(bookEntity));

        List<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Java超入門", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(BookEntity.class))).thenAnswer(invocation -> {
            BookEntity saved = invocation.getArgument(0);
            saved.setId(1);
            return saved;
        });

        Book createBook = bookService.createBook(book);

        assertNotNull(createBook);
        assertEquals(1, createBook.getId());
        assertEquals("Java超入門", createBook.getTitle());
        verify(bookRepository).save(any(BookEntity.class));
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(any(BookEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book updateBook = bookService.updateBook(1, book);

        assertEquals(1, updateBook.getId());
        assertEquals("Java超入門", updateBook.getTitle());
        verify(bookRepository).findById(1);
        verify(bookRepository).save(any(BookEntity.class));
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1);

        bookService.deleteBook(1);

        verify(bookRepository, times(1)).deleteById(1);
    }
}
