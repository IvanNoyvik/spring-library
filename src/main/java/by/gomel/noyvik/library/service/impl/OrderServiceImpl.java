package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.dao.BookDao;
import by.gomel.noyvik.library.persistence.dao.OrderDao;
import by.gomel.noyvik.library.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl extends AbstractCrudService<Order> implements OrderService {

    private final OrderDao orderDao = PROVIDER_DAO.getOrderDao();
    private final BookDao bookDao = PROVIDER_DAO.getBookDao();


    @Override
    public List<Order> findByBookId(Long id) {
        return orderDao.findByBookId(id);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderDao.findByUserId(id);
    }

    @Override
    public List<Order> findAllOverdueOrder() {

        List<Order> orders = orderDao.findAll();

        return getOverdueOrders(orders);
    }

    @Override
    public boolean userHaveBook(Long bookId, Long userId) {
        return orderDao.findByBookAndUserId(bookId, userId);
    }

    @Override
    public Order addOrder(User user, Long bookID, int duration) {

        Book book = bookDao.findById(bookID);

        if (book.getQuantity() > 0 && !userHaveBook(bookID, user.getId())) {
            Order order = new Order(LocalDate.now(), duration, book, user);

            Order newOrder = orderDao.save(order);

            book.setQuantity(book.getQuantity() - 1);
            bookDao.update(book);

            return newOrder;

        } else {

            throw new ServiceException();

        }
    }

    private List<Order> getOverdueOrders(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

}
