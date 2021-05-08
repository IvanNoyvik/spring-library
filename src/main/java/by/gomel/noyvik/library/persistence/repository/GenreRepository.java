package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenre(String genre);

}
