package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Author;

public interface AuthorService extends CrudService<Author> {
    Author save(String author);

    boolean isExists(String author);
}
