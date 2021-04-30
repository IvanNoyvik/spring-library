package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.User;

import java.util.List;


public interface OrderService  {

    List<Order> findByBookId(Long id);

    List<Order> findByUserId(Long id);

    List<Order> findAllOverdueOrder();

    boolean userHaveBook(Long bookId, Long userId);

    Order addOrder(User user, Long bookID, int duration);


}
