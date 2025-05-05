package com.college.yi.bookmanager.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.model.BookEntity;
import com.college.yi.bookmanager.repository.BookRepository;


@Service
public class BookService {
	
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	
	public List<Book> getAllBooks() {
		List<BookEntity> bookEntity = bookRepository.findAll();
		
		if(bookEntity == null) {
			throw new EntityNotFoundException("404 Not Found");
		}
		
		List<Book> bookList = new ArrayList<>();
		for(BookEntity books : bookEntity) {
			Book book = new Book();
			book.setId(books.getId());
			book.setTitle(books.getTitle());
			book.setAuthor(books.getAuthor());
			book.setPublisher(books.getPublisher());
			book.setPublished_date(books.getPublished_date());
			book.setStock(books.getStock());
			
			bookList.add(book);
		}
		
		return bookList;
	} 
	
	//登録
	public Book createBook(Book book){
		BookEntity entity = new BookEntity();
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		entity.setPublisher(book.getPublisher());
		entity.setPublished_date(book.getPublished_date());
		entity.setStock(book.getStock());
		
		BookEntity save = bookRepository.save(entity);
		book.setId(save.getId());
		
		return book;
	}
	
	//更新
	   public Book updateBook(int id, Book book) {
	        BookEntity entity = bookRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("登録されていません"));

	        entity.setTitle(book.getTitle());
	        entity.setAuthor(book.getAuthor());
	        entity.setPublisher(book.getPublisher());
	        entity.setPublished_date(book.getPublished_date());
	        entity.setStock(book.getStock());

	        BookEntity update = bookRepository.save(entity);

	        book.setId(update.getId());
	        return book;
	}
	
		//削除
	   public void deleteBook(int id) {
		   	bookRepository.deleteById(id);
	}
}
