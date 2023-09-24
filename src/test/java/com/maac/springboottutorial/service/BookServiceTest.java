package com.maac.springboottutorial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.maac.springboottutorial.db.entity.Book;
import com.maac.springboottutorial.db.repository.BookRepository;
import com.maac.springboottutorial.dto.BookDTO;

@SpringBootTest
public class BookServiceTest {

    private final BookService bookService;

    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = Book
                .builder()
                .author("Anahit")
                .id(1L)
                .isbn("11111")
                .title("2")
                .build();
        List<Book> bookList = Collections.singletonList(book);
        Mockito.when(bookRepository.findByTitleIgnoreCase("2")).thenReturn(bookList);
    }

    @Test
    public void shouldReturnBookWhenBookWithTitleFound() {
        String bookTitle = "2";
        BookDTO existingBook = bookService.findBookByTitle(bookTitle);
        assertEquals(bookTitle, existingBook.getTitle());
    }
}
