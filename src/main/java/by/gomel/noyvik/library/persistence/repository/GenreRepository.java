package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("from Genre g left join fetch g.books where g.genre = :genre")
    Genre findByGenre(@Param("genre") String genre);

    @Query("from Genre g left join fetch g.books")
    List<Genre> findAllWithBook();

}
