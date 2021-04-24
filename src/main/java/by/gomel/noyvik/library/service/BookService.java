package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Book;

public interface BookService extends CrudService<Book> {



    byte[] findImageById(Long id);

    void addImage(Long id, byte[] image);

    boolean findByTitleAndAuthor(String title, String author);

    Book save(String title, String description, int quantity, String[] genres, String author);

    Book update(Long bookId, String title, String description, int quantity, String[] genres, String author);
}
