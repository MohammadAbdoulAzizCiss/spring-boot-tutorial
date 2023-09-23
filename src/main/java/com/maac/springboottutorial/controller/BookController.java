package com.maac.springboottutorial.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/")
    public String getAllBooks() {
        return "Get All Books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id) {
        return ("Get book with id :" + id);
    }

    @PostMapping("/")
    public String createBook() {
        return "Adding a book";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id) {
        return ("updating infos about book with the id " + id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        return "deleting book with id " + id;
    }
}
