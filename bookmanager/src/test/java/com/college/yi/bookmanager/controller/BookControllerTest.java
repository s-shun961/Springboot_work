package com.college.yi.bookmanager.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Collections;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;
    private Book book;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setId(1);
        book.setTitle("Java超入門");
        book.setAuthor("山田 太郎");
        book.setPublisher("技術評論社");
        book.setPublishedDate(LocalDate.of(2023,9,1));
        book.setStock(10);
    }

    @Test
    void tesGetBooks() throws Exception {
    	when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));
    	mockMvc.perform(get("/api/books"))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$[0].title").value("Java超入門"));
    }
    
    @Test
    void testGetBooks_NotFound() throws Exception {
        when(bookService.getAllBooks()).thenThrow(new EntityNotFoundException("404 Not Found"));

        mockMvc.perform(get("/api/books"))
               .andExpect(status().isNotFound());
    }
    
    @Test
    void testCreateBook() throws Exception {
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
               .content("""
                   {
                     "title": "Java超入門",
                     "author": "山田太郎",
                     "publisher": "技術評論社",
                     "publishedDate": "2023-09-01",
                     "stock": 10
                   }
               """))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("Java超入門"));
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.updateBook(eq(1), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1")
               .content("""
                   {
                     "title": "Java超入門",
                     "author": "山田太郎",
                     "publisher": "技術評論社",
                     "publishedDate": "2023-09-01",
                     "stock": 10
                   }
               """))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Java超入門"));
    }

    @Test
    void testUpdateBook_NotFound() throws Exception {
        when(bookService.updateBook(eq(999), any(Book.class)))
                .thenThrow(new EntityNotFoundException("404 Not Found"));

        mockMvc.perform(put("/api/books/999")
               .content("""
                   {
                     "title": "更新失敗",
                     "author": "名無し",
                     "publisher": "不明",
                     "publishedDate": "2023-01-01",
                     "stock": 10
                   }
               """))
               .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/api/books/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        doThrow(new EntityNotFoundException("404 Not Found")).when(bookService).deleteBook(999);

        mockMvc.perform(delete("/api/books/999"))
               .andExpect(status().isNotFound());
    }
}
