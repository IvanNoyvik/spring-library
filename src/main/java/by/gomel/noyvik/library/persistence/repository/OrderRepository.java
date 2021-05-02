package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBookId(Long id);
    List<Order> findByUserId(Long id);

//    List<Order> findAllOverdueOrder();

    boolean findByBookIdAndUserId(Long bookId, Long userId);

    List<Order> findByUserIdAndDateReceivingBefore(Long userId, LocalDate date);

    void removeAllByUserId(Long userId);
}
