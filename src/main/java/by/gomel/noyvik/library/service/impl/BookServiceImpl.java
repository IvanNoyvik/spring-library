package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.persistence.dao.AuthorDao;
import by.gomel.noyvik.library.persistence.dao.BookDao;
import by.gomel.noyvik.library.service.BookService;

public class BookServiceImpl extends AbstractCrudService<Book> implements BookService {

    private final BookDao bookDao = PROVIDER_DAO.getBookDao();
    private final AuthorDao authorDao = PROVIDER_DAO.getAuthorDao();


    @Override
    public byte[] findImageById(Long id) {
        return bookDao.findImageById(id);
    }

    @Override
    public void addImage(Long id, byte[] image) {
        bookDao.addImage(id, image);
    }

    @Override
    public boolean findByTitleAndAuthor(String title, String author) {

        return !title.isEmpty() && !author.isEmpty() && bookDao.findByTitleAndAuthor(title, author);
    }

    @Override
    public Book save(String title, String description, int quantity, String[] genreName, String authorName) {

        if (!bookDao.findByTitleAndAuthor(title, authorName)) {

            try {
                Book book = new Book(title, description, quantity);
                Author author = authorDao.findByAuthor(authorName);
                book.setAuthor(author);
                return bookDao.save(book, genreName);
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
            return bookDao.update(bookId, title, description, quantity, genres, author);
        } catch (DaoPartException e) {
            throw new SecurityException();
        }


    }
}
