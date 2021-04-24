package by.gomel.noyvik.library.persistence.dao;


import by.gomel.noyvik.library.model.Genre;

public interface GenreDao extends CrudDao<Genre> {

    Genre findByGenre(String genre);

}
