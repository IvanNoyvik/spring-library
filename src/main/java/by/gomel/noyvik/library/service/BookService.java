package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {


    Page<Book> findPageBooks(int page);

    byte[] findImageById(Long id);

    void addImage(Long id, MultipartFile file);

    boolean isExists(String title, String author);

    Book save(Book book);

    Book update(Book book);

    Book findBookById(Long bookId);
}
