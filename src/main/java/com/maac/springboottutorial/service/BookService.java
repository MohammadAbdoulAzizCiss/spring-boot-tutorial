package com.maac.springboottutorial.service;

import java.util.List;

import com.maac.springboottutorial.dto.BookDTO;
import com.maac.springboottutorial.error.exception.BookNotFoundException;
import com.maac.springboottutorial.error.exception.DuplicateBookException;

public interface BookService {

    BookDTO saveNewBook(BookDTO book) throws DuplicateBookException;

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id) throws BookNotFoundException;

    void removeBookById(Long id) throws BookNotFoundException;

    BookDTO updateBook(Long id, BookDTO bookDto) throws BookNotFoundException;

    BookDTO findBookByTitle(String bookTitle) throws BookNotFoundException;

}
