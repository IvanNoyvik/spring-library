package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.persistence.repository.AuthorRepository;
import by.gomel.noyvik.library.persistence.repository.BookRepository;
import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;




    @Override
    public Page<Book> findPageBooks(int page){

        return bookRepository.findAll(PageRequest.of(page, 5));

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
    public Book update(Book book) {

        try {
//            return bookRepository.update(bookId, title, description, quantity, genres, author);
            return null;
        } catch (DaoPartException e) {
            throw new SecurityException();
        }


    }

    @Override
    public Book findBookById(Long bookId) {

        return bookRepository.findFullBookById(bookId);
    }
}
