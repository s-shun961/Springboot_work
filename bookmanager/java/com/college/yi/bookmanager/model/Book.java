package com.college.yi.bookmanager.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
	
private int id;
private String title;
private String author;
private String publisher;
private LocalDate publicationDate;
private int stock;

}
