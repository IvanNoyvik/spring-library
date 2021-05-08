package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Genre;

import java.util.List;

public interface GenreService {


    boolean isExists(String genre);

    List<Genre> findAll();

    void addGenre(Genre genre);

}
