package com.maac.springboottutorial.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maac.springboottutorial.db.entity.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByTitleIgnoreCase(String title);
}
