package by.gomel.noyvik.library.persistence.dao;

import by.gomel.noyvik.library.model.Author;

public interface AuthorDao extends CrudDao<Author> {

    Author findByAuthor(String author);

}
