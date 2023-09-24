package com.maac.springboottutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.maac.springboottutorial.db.entity.Book;
import com.maac.springboottutorial.db.repository.BookRepository;
import com.maac.springboottutorial.dto.BookDTO;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO saveNewBook(BookDTO bookDto) {
        Book book = dtoToEntity(bookDto);
        Book savedBook = this.bookRepository.save(book);
        return entityToDto(savedBook);

    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent())
            return entityToDto(bookOptional.get());
        return null;

    }

    private Book dtoToEntity(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setIsbn(bookDto.getIsbn());
        return book;

    }

    private BookDTO entityToDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setIsbn(book.getIsbn());
        return bookDto;

    }

    @Override
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDto) {
        Book book = bookRepository.findById(id).get();

        if (bookDto.getTitle() != null || !bookDto.getTitle().isBlank())
            book.setTitle(bookDto.getTitle());

        if (bookDto.getAuthor() != null || !bookDto.getAuthor().isBlank())
            book.setAuthor(bookDto.getAuthor());

        if (bookDto.getIsbn() != null || !bookDto.getIsbn().isBlank())
            book.setIsbn(bookDto.getIsbn());

        if (bookDto.getPublicationYear() > 0)
            book.setPublicationYear(bookDto.getPublicationYear());

        return entityToDto(bookRepository.save(book));

    }

}
