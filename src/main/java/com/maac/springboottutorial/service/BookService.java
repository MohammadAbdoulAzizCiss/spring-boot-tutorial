package com.maac.springboottutorial.service;

import java.util.List;

import com.maac.springboottutorial.dto.BookDTO;
import com.maac.springboottutorial.error.exception.BookNotFoundException;

public interface BookService {

    BookDTO saveNewBook(BookDTO book);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id) throws BookNotFoundException;

    void removeBookById(Long id);

    BookDTO updateBook(Long id, BookDTO bookDto);

    BookDTO findBookByTitle(String bookTitle);

}
