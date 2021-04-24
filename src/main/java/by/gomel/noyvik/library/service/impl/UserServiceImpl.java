package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.Status;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.dao.OrderDao;
import by.gomel.noyvik.library.persistence.dao.StatusDao;
import by.gomel.noyvik.library.persistence.dao.UserDao;
import by.gomel.noyvik.library.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.LIMITED;
import static by.gomel.noyvik.library.controller.constant.CommandConstant.LOCKED;

public class UserServiceImpl extends AbstractCrudService<User> implements UserService {

    private final UserDao userDao = PROVIDER_DAO.getUserDao();
    private final StatusDao statusDao = PROVIDER_DAO.getStatusDao();
    private final OrderDao orderDao = PROVIDER_DAO.getOrderDao();


    @Override
    public User findByLoginAndPassword(String login, String password) {

        User user;

        try {
            user = userDao.findByLoginAndPassword(login, password);
        } catch (DaoPartException e) {

            throw new ServiceException(e);
        }

        if (user != null) {

            if (user.getStatus().getStatus().equalsIgnoreCase(LIMITED) && user.getAuthenticate().getUnlockedDate().isBefore(LocalDate.now())) {

                user.addStatus(statusDao.getOkStatus());
                userDao.update(user);

            }
        }
        return user;
    }

    @Override
    public boolean isExists(String login) {
        User user;
        try {

            user = userDao.findByLogin(login);
        } catch (DaoPartException e) {
            throw new ServiceException(e);
        }
        return user != null;
    }

    @Override
    public User registration(String login, String password, String name, String email) {

        if (!isExists(login)) {

            try {

                Authenticate authenticate = new Authenticate(login, password);
                User user = new User(name, email);
                user.addAuthenticate(authenticate);

                Status status = statusDao.getOkStatus();
                user.addStatus(status);

                return userDao.save(user);

            } catch (Exception e) {
                throw new ServiceException(e);
            }

        }

        return null;

    }


    @Override
    public Map<User, Integer> findUserWithCountOverdueOrder() {

        List<Object[]> allUsersWithOrder = userDao.findAllWithOrder();

        Map<User, List<Order>> userWithAllOrders = mapper(allUsersWithOrder);

        Map<User, Integer> userWithCountOverdueOrder = new LinkedHashMap<>();

        for (User user : userWithAllOrders.keySet()) {

            Integer countOverdueOrder = getCountOverdueOrder(userWithAllOrders.get(user));
            userWithCountOverdueOrder.put(user, countOverdueOrder);

        }

        return userWithCountOverdueOrder;
    }

    @Override
    public boolean changeStatus(Long userId, String status, int duration) {
        try {
            User user = userDao.findById(userId);
            if (!user.getStatus().getStatus().equals(status)) {

                if (status.equals(LOCKED) && !user.getOrders().isEmpty()) {

                    orderDao.removeAllOrder(userId);
                    userDao.changeStatus(userId, status, duration);

                } else {

                    userDao.changeStatus(user, status, duration);
                }

            }
        } catch (DaoPartException e) {

            return false;

        } catch (Exception e) {
            throw new ServiceException(e);
        }


        return true;

    }


    private Map<User, List<Order>> mapper(List<Object[]> userOrderList) {

        Map<User, List<Order>> userWithAllOrders = new LinkedHashMap<>();

        for (Object[] userOrder : userOrderList) {

            User user = (User) userOrder[0];
            Order order = (Order) userOrder[1];

            if (!userWithAllOrders.containsKey(user)) {
                List<Order> orders = new ArrayList<>();
                if (order != null) {
                    orders.add(order);
                }
                userWithAllOrders.put(user, orders);
            } else {
                if (order != null) {
                    userWithAllOrders.get(user).add(order);
                }
            }
        }
        return userWithAllOrders;
    }

    private int getCountOverdueOrder(List<Order> orders) {

        return (int) orders.stream().
                filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
                .count();

    }


}
