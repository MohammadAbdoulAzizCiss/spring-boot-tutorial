package com.maac.springboottutorial.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maac.springboottutorial.dto.BookDTO;
import com.maac.springboottutorial.error.exception.BookNotFoundException;
import com.maac.springboottutorial.error.exception.DuplicateBookException;
import com.maac.springboottutorial.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable("id") Long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping("/")
    public BookDTO createBook(@Valid @RequestBody BookDTO book) throws DuplicateBookException {
        return this.bookService.saveNewBook(book);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@Valid @PathVariable("id") Long id, @RequestBody BookDTO bookDto)
            throws BookNotFoundException {
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) throws BookNotFoundException {
        bookService.removeBookById(id);
        return "deleted!";
    }

    @GetMapping("/title/{title}")
    public BookDTO getByName(@PathVariable("title") String title) throws BookNotFoundException {
        return this.bookService.findBookByTitle(title);
    }
}
