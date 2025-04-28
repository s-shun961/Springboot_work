package com.college.yi.bookmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.model.Book;

@Service
public class BookService {

	public List<Book> findAll(){
		List<Book> bookList = new ArrayList<>();
	
				bookList.add(new Book(0001,"タイトル1","著者1","出版社1",LocalDate.of(2025,4,25),10));
				bookList.add(new Book(0002,"タイトル2","著者2","出版社2",LocalDate.of(2025,4,25),10));
				bookList.add(new Book(0003,"タイトル3","著者3","出版社3",LocalDate.of(2025,4,25),10));
				bookList.add(new Book(0004,"タイトル4","著者4","出版社4",LocalDate.of(2025,4,25),10));
				bookList.add(new Book(0005,"タイトル5","著者5","出版社5",LocalDate.of(2025,4,25),10));
				
				return bookList;
	}
}
