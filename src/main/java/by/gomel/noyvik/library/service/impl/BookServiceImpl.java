package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.persistence.repository.AuthorRepository;
import by.gomel.noyvik.library.persistence.repository.BookRepository;
import by.gomel.noyvik.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public byte[] findImageById(Long id) {
        return bookRepository.findImageById(id);
    }

    @Override
    public void addImage(Long id, byte[] image) {
//        bookRepository.addImage(id, image);
    }

    @Override
    public boolean findByTitleAndAuthor(String title, String author) {

        return !title.isEmpty() && !author.isEmpty() && bookRepository.existsByTitleAndAuthorAuthor(title, author);
    }

    @Override
    public Book save(String title, String description, int quantity, String[] genreName, String authorName) {

        if (!bookRepository.existsByTitleAndAuthorAuthor(title, authorName)) {

            try {
                Book book = new Book(title, description, quantity);
                Author author = authorRepository.findByAuthor(authorName);
                book.setAuthor(author);
//                return bookRepository.save(book, genreName);
                return null;
            } catch (DaoPartException e) {
                throw new SecurityException();
            }
        } else {
            return null;
        }

    }

    @Override
    public Book update(Long bookId, String title, String description, int quantity, String[] genres, String author) {

        try {
//            return bookRepository.update(bookId, title, description, quantity, genres, author);
            return null;
        } catch (DaoPartException e) {
            throw new SecurityException();
        }


    }






    //todo
    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
