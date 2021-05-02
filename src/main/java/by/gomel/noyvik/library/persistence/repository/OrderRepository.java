package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBookId(Long id);

    @Query("from Order o left join fetch o.book b left join fetch b.author where o.user.id = :id")
    List<Order> findByUserId(@Param("id") Long id);

//    List<Order> findAllOverdueOrder();

    boolean findByBookIdAndUserId(Long bookId, Long userId);

    List<Order> findByUserIdAndDateReceivingBefore(Long userId, LocalDate date);

    void removeAllByUserId(Long userId);
}
