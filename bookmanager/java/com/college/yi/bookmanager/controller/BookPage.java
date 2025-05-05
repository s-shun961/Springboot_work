package com.college.yi.bookmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;

@Controller
@RequestMapping("/books")
public class BookPage {
	private final BookService bookService;
	
	public BookPage(BookService bookService) {
		this.bookService = bookService;
	}
	
@GetMapping
public String booksPage(Model model) {
	// bookServiceから書籍一覧をとってきてmodelにbookListという名前で渡している
	List<Book> bookList = bookService.getAllBooks();
	model.addAttribute("bookList",bookList);
	// index.htmlを表示
	return "index";
	}

}
