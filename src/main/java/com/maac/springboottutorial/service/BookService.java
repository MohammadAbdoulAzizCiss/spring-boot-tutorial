package com.maac.springboottutorial.service;

import java.util.List;

import com.maac.springboottutorial.dto.BookDTO;

public interface BookService {

    BookDTO saveNewBook(BookDTO book);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    void removeBookById(Long id);

    BookDTO updateBook(Long id, BookDTO bookDto);

}
