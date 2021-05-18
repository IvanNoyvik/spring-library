package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Author;

import java.util.List;

public interface AuthorService {

    boolean isExists(String author);

    List<Author> findAll();

    void addAuthor(Author author);
}
