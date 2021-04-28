package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b.image from Book b where b.id = :id")
    byte[] findImageById(@Param("id") Long id);

//    void addImage(Long id, byte[] image);


    @Query("from Book b left join fetch b.author")
    @Override
    List<Book> findAll();

    boolean existsByTitleAndAuthorAuthor(String title, String author);



    @Override
    @Query(value = "SELECT b FROM Book b LEFT JOIN FETCH b.author",
            countQuery = "select count(b) from Book b")
    Page<Book> findAll(Pageable pageable);
}
