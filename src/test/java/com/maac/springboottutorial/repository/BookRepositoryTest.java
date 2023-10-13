package com.maac.springboottutorial.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.maac.springboottutorial.db.entity.Book;
import com.maac.springboottutorial.db.repository.BookRepository;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void givenBookObject_whenSaving_shouldReturnBookObject() {
        Book b1 = Book.builder()
                .id(1L)
                .title("titre")
                .author("Auteur")
                .isbn("xx-xxxxx-xxxx-x")
                .publicationYear(2010)
                .build();
        Book savedBook = bookRepository.save(b1);
        assertEquals(1L, savedBook.getId());
    }
}
