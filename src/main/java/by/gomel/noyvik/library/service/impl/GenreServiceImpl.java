package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.persistence.repository.GenreRepository;
import by.gomel.noyvik.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) {
        if (!isExists(genre.getGenre())) {
            genreRepository.save(genre);
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public boolean isExists(String genreStr) {
        return genreRepository.findByGenre(genreStr) != null;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


}
