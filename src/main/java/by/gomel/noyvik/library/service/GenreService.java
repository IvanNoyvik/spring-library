package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Genre;

public interface GenreService extends CrudService<Genre> {

    Genre save(String genre);

    boolean isExists(String genre);
}
