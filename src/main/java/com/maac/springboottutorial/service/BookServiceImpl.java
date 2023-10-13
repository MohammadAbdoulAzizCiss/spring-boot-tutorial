package com.maac.springboottutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.maac.springboottutorial.db.entity.Book;
import com.maac.springboottutorial.db.repository.BookRepository;
import com.maac.springboottutorial.dto.BookDTO;
import com.maac.springboottutorial.error.exception.BookNotFoundException;
import com.maac.springboottutorial.error.exception.DuplicateBookException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Saves a new book based on the provided BookDTO.
     *
     * This method creates a new book entity by mapping the attributes from the
     * given BookDTO.
     * It checks if a book with the same title and author already exists.
     * If a duplicate book is found, a DuplicateBookException is thrown. If not, the
     * new book is saved
     * to the repository.
     * The saved book is then converted to a BookDTO and returned.
     *
     * @param bookDto The BookDTO representing the book to be saved.
     * @return A BookDTO representing the newly saved book.
     * @throws DuplicateBookException If a book with the same title and author
     *                                already exists.
     */
    @Override
    public BookDTO saveNewBook(BookDTO bookDto) throws DuplicateBookException {
        Book book = this.toEntity.apply(bookDto);
        var bookOptional = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (bookOptional.isPresent())
            throw new DuplicateBookException("Book already exists");
        Book savedBook = this.bookRepository.save(book);
        return this.toDto.apply(savedBook);

    }

    /**
     * return a list containing the Book existing.
     * 
     * This method retrieves all the Books and then map the Book Entity to the Book
     * DTO before collecting it back to a List.
     * 
     * @return List<BookDTO>
     */
    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(this.toDto).collect(Collectors.toList());
    }

    /**
     * Get a Book based on the given ID
     * 
     * This method check if the Optional of the Book with the given id is present
     * and returns it.If a Book with the gven id is not found a
     * BookNotFoundException is thrown
     * 
     * @param id The id of type Long representing the id of the Book
     * @return A BookDTO representing the found book
     * @throws BookNotFoundException If the book with the given Id is not found
     */

    @Override
    public BookDTO getBookById(Long id) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent())
            return this.toDto.apply(bookOptional.get());
        throw new BookNotFoundException("Book does not exist");

    }

    @Override
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO findBookByTitle(String bookTitle) {
        return this.toDto.apply(bookRepository.findByTitleIgnoreCase(bookTitle).get(0));
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDto) throws BookNotFoundException {
        Optional<Book> existingBookEntityOptional = bookRepository.findById(id);

        Book existingBook = existingBookEntityOptional
                .orElseThrow(() -> new BookNotFoundException("Book does not exist"));

        // ofNullable --> create an optional with the value given(can be null)
        // filter --> check if the field is not not blank
        // orElse --> return the value of the optional if not null or the value given

        existingBook.setTitle(
                Optional
                        .ofNullable(bookDto.getTitle())
                        .filter(title -> !title.isBlank())
                        .orElse(existingBook.getTitle()));

        existingBook.setAuthor(
                Optional
                        .ofNullable(bookDto.getAuthor())
                        .filter(author -> !author.isBlank())
                        .orElse(existingBook.getAuthor()));

        existingBook.setAuthor(
                Optional.ofNullable(bookDto.getAuthor())
                        .filter(author -> !author.isBlank())
                        .orElse(existingBook.getAuthor()));

        existingBook.setIsbn(
                Optional.ofNullable(bookDto.getIsbn())
                        .filter(isbn -> !isbn.isBlank())
                        .orElse(existingBook.getIsbn()));

        existingBook.setPublicationYear(
                Optional.of(bookDto.getPublicationYear())
                        .filter(year -> year > 0)
                        .orElse(existingBook.getPublicationYear()));

        return this.toDto.apply(bookRepository.save(existingBook));

    }

    // private methods

    /**
     * Converts a BookDTO object to a Book entity.
     *
     * This function takes a BookDTO as input and creates a new Book entity
     * by mapping its attributes to the corresponding fields of the entity.
     *
     * @param bookDto The BookDTO object to be converted.
     * @return A Book entity representing the input BookDTO.
     */
    private Function<BookDTO, Book> toEntity = bookDto -> {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setIsbn(bookDto.getIsbn());
        return book;
    };

    /**
     * Converts a Book entity to a BookDTO object.
     *
     * This function takes a Book entity as input and creates a new BookDTO object
     * by mapping its attributes to the corresponding fields of the DTO.
     *
     * @param book The Book entity to be converted.
     * @return A BookDTO representing the input Book entity.
     */
    private Function<Book, BookDTO> toDto = book -> {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setIsbn(book.getIsbn());
        return bookDto;
    };

}
