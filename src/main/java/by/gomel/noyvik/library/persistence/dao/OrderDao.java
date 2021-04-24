package by.gomel.noyvik.library.persistence.dao;

import by.gomel.noyvik.library.model.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order> {

    List<Order> findByBookId(Long id);

    List<Order> findByUserId(Long id);

//    List<Order> findAllOverdueOrder();

    boolean findByBookAndUserId(Long bookId, Long userId);

    List<Order> findAllOrdersByUserId(Long userId);

    void removeAllOrder(Long userId);
}
