package by.gomel.noyvik.library.persistance.dao;

import by.gomel.noyvik.library.model.Book;

public interface BookDao extends CrudDao<Book> {

    byte[] findImageById(Long id);

    void addImage(Long id, byte[] image);

    boolean findByTitleAndAuthor(String title, String author);

    Book save(Book book, String[] genreName);

    Book update(Long bookId, String title, String description, int quantity, String[] genres, String author);

}
