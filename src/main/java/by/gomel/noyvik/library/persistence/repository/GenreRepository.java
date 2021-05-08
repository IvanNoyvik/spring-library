package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenre(String genre);

    @Query("from Genre g left join fetch g.books")
    List<Genre> findAllWithBook();

}
