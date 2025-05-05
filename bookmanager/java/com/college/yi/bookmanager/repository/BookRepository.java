package com.college.yi.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.yi.bookmanager.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>{

}
