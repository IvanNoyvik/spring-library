package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderService {

    List<Order> findByBookId(Long id);

    @Query("from Order o left join fetch o.book where o.user.id = :id")
    List<Order> findByUserId(@Param("id") Long id);

    List<Order> findAllOverdueOrder();

    boolean userHaveBook(Long bookId, Long userId);

    Order addOrder(Order order);

    boolean returnOrder(Long id, Long bookId);
}
