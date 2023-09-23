package com.maac.springboottutorial.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maac.springboottutorial.db.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
