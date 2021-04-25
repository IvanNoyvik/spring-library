package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByAuthor(String author);

}
