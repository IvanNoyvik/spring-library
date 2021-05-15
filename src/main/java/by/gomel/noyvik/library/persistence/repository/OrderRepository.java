package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("from Order o left join fetch o.user u join fetch u.authenticate where o.book.id = :bookId")
    List<Order> findByBookId(@Param("bookId") Long bookId);

    @Query("from Order o left join fetch o.book b left join fetch b.author where o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);

    @Query("from Order o left join fetch o.user u left join fetch u.status join fetch u.authenticate left join fetch o.book")
    List<Order> findAllWithUserAuthenticateAndBook();

    boolean existsByBookIdAndUserId(Long bookId, Long userId);

    List<Order> findByUserIdAndDateReceivingBefore(Long userId, LocalDate date);

    void removeAllByUserId(Long userId);

}
