package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Genre;

public interface GenreService {

    Genre save(String genre);

    boolean isExists(String genre);
}
