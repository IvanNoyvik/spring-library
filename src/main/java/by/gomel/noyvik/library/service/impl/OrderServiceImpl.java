package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.repository.OrderRepository;
import by.gomel.noyvik.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
//    private final BookDao bookDao = PROVIDER_DAO.getBookDao();


    @Override
    public List<Order> findByBookId(Long id) {
        return orderRepository.findByBookId(id);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> findAllOverdueOrder() {

        List<Order> orders = orderRepository.findAll();

        return getOverdueOrders(orders);
    }

    @Override
    public boolean userHaveBook(Long bookId, Long userId) {
        return orderRepository.findByBookIdAndUserId(bookId, userId);
    }

    @Override
    public Order addOrder(User user, Long bookID, int duration) {

//        Book book = bookDao.findById(bookID);
//
//        if (book.getQuantity() > 0 && !userHaveBook(bookID, user.getId())) {
//            Order order = new Order(LocalDate.now(), duration, book, user);
//
//            Order newOrder = orderDao.save(order);
//
//            book.setQuantity(book.getQuantity() - 1);
//            bookDao.update(book);
//
//            return newOrder;
//
//        } else {

            throw new ServiceException();

//        }
    }

    private List<Order> getOverdueOrders(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

}
