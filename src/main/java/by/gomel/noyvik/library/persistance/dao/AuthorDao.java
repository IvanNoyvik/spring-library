package by.gomel.noyvik.library.persistance.dao;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.Author;

public interface AuthorDao extends CrudDao<Author> {

    Author findByAuthor(String author);

}
