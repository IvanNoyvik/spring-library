package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b.image from Book b where b.id = :id")
    byte[] findImageById(@Param("id") Long id);


    @Query("from Book b left join fetch b.author")
    @Override
    List<Book> findAll();

    boolean existsByTitleAndAuthorAuthor(String title, String author);

    @Modifying
    @Transactional
    @Query("update Book b set b.quantity = (b.quantity + :quantity) where b.id = :id")
    void changeQuantityByBookId(@Param("id") Long id, @Param("quantity") int quantity);


    @Override
    @Query(value = "from Book b left join fetch b.author",
            countQuery = "select count(b) from Book b")
    Page<Book> findAll(Pageable pageable);

    @Query("from Book b left join fetch b.author left join fetch b.genres where b.id = :bookId")
    Book findFullBookById(@Param("bookId") Long bookId);


    @Transactional
    @Modifying
    @Query("update Book set image = :image where id = :id")
    void imageBulkUpdate(@Param("id") Long id, @Param("image") byte[] image);
}
