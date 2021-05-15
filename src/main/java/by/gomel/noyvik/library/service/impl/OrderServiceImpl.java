package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.persistence.repository.BookRepository;
import by.gomel.noyvik.library.persistence.repository.OrderRepository;
import by.gomel.noyvik.library.persistence.repository.UserRepository;
import by.gomel.noyvik.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


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

        List<Order> orders = orderRepository.findAllWithUserAuthenticateAndBook();

        return getOverdueOrders(orders);
    }

    @Override
    public boolean userHaveBook(Long bookId, Long userId) {
        return orderRepository.existsByBookIdAndUserId(bookId, userId);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class, Exception.class})
    @Modifying
    public Order addOrder(Order order) {

        Long bookId = order.getBook().getId();
        Long userId = order.getUser().getId();

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ServiceException("book not find"));

        if (book.getQuantity() > 0 && !userHaveBook(bookId, userId)) {

            order = orderRepository.save(order);

            bookRepository.changeQuantityByBookId(bookId, -1);

            return order;

        } else {

            throw new ServiceException();

        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    @Modifying
    public boolean returnOrder(Long id, Long bookId) {

        try {
            bookRepository.changeQuantityByBookId(bookId, 1);
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {

            throw new ServiceException(e);
        }

    }

    private List<Order> getOverdueOrders(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

}
