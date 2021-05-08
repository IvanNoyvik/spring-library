package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Book;
import org.springframework.data.domain.Page;

public interface BookService {


    Page<Book> findPageBooks(int page);

    byte[] findImageById(Long id);

    void addImage(Long id, byte[] image);

    boolean findByTitleAndAuthor(String title, String author);

    Book save(String title, String description, int quantity, String[] genres, String author);

    Book update(Book book);

    Book findBookById(Long bookId);
}
