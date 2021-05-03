package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.repository.RoleRepository;
import by.gomel.noyvik.library.persistence.repository.StatusRepository;
import by.gomel.noyvik.library.persistence.repository.UserRepository;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;


    @Override
    public User login(Authenticate authenticate) {

        User user;

        user = userRepository.findByLoginAndPassword(authenticate.getLogin(), authenticate.getPassword());

        if (user != null) {

            if (user.getStatus().getStatus().equalsIgnoreCase(LIMITED) && user.getAuthenticate().getUnlockedDate().isBefore(LocalDate.now())) {

                user.addStatus(statusRepository.findByStatus(OK));
                userRepository.save(user);

            }
        }
        return user;
    }

    @Override
    public boolean isExists(String login) {
        User user;
        try {

            user = userRepository.findByAuthenticateLogin(login);
        } catch (DaoPartException e) {
            throw new ServiceException(e);
        }
        return user != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User createNewUser(User user) {

        if (isExists(user.getAuthenticate().getLogin())){
            throw new ServiceException(USER_EXISTS);
        }

        user.setStatus(statusRepository.findByStatus(OK));
        user.addRole(roleRepository.findByRole(ROLE_USER));

        return userRepository.save(user);

    }

    @Override
    public User updateUser(User userForUpdate) {

        return userRepository.save(userForUpdate);
    }

}
//
//    @Override
//    public User registration(String login, String password, String name, String email) {
//
//        if (!isExists(login)) {
//
//            try {
//
//                Authenticate authenticate = new Authenticate(login, password);
//                User user = new User(name, email);
//                user.addAuthenticate(authenticate);
//
//                Status status = statusDao.getOkStatus();
//                user.addStatus(status);
//
//                return userDao.save(user);
//
//            } catch (Exception e) {
//                throw new ServiceException(e);
//            }
//
//        }
//
//        return null;
//
//    }
//
//
//    @Override
//    public Map<User, Integer> findUserWithCountOverdueOrder() {
//
//        List<Object[]> allUsersWithOrder = userDao.findAllWithOrder();
//
//        Map<User, List<Order>> userWithAllOrders = mapper(allUsersWithOrder);
//
//        Map<User, Integer> userWithCountOverdueOrder = new LinkedHashMap<>();
//
//        for (User user : userWithAllOrders.keySet()) {
//
//            Integer countOverdueOrder = getCountOverdueOrder(userWithAllOrders.get(user));
//            userWithCountOverdueOrder.put(user, countOverdueOrder);
//
//        }
//
//        return userWithCountOverdueOrder;
//    }
//
//    @Override
//    public boolean changeStatus(Long userId, String status, int duration) {
//        try {
//            User user = userDao.findById(userId);
//            if (!user.getStatus().getStatus().equals(status)) {
//
//                if (status.equals(LOCKED) && !user.getOrders().isEmpty()) {
//
//                    orderDao.removeAllOrder(userId);
//                    userDao.changeStatus(userId, status, duration);
//
//                } else {
//
//                    userDao.changeStatus(user, status, duration);
//                }
//
//            }
//        } catch (DaoPartException e) {
//
//            return false;
//
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//
//
//        return true;
//
//    }
//
//
//    private Map<User, List<Order>> mapper(List<Object[]> userOrderList) {
//
//        Map<User, List<Order>> userWithAllOrders = new LinkedHashMap<>();
//
//        for (Object[] userOrder : userOrderList) {
//
//            User user = (User) userOrder[0];
//            Order order = (Order) userOrder[1];
//
//            if (!userWithAllOrders.containsKey(user)) {
//                List<Order> orders = new ArrayList<>();
//                if (order != null) {
//                    orders.add(order);
//                }
//                userWithAllOrders.put(user, orders);
//            } else {
//                if (order != null) {
//                    userWithAllOrders.get(user).add(order);
//                }
//            }
//        }
//        return userWithAllOrders;
//    }
//
//    private int getCountOverdueOrder(List<Order> orders) {
//
//        return (int) orders.stream().
//                filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
//                .count();
//
//    }
