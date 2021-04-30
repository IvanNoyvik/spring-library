package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Author;

public interface AuthorService {
    Author save(String author);

    boolean isExists(String author);
}
