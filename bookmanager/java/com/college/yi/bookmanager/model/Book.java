package com.college.yi.bookmanager.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private int id;
	private String title;
	private String author;
	private String publisher;
	private LocalDate publishedDate;
	private int stock;

}
