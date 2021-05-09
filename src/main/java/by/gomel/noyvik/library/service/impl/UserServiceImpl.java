package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.repository.RoleRepository;
import by.gomel.noyvik.library.persistence.repository.StatusRepository;
import by.gomel.noyvik.library.persistence.repository.UserRepository;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @Modifying
    public User createNewUser(User user) {

        if (isExists(user.getAuthenticate().getLogin())) {
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

    @Override
    public boolean isAdministrator(User user) {

        if (user != null) {

            return user.getRoles().stream().map(Role::getRole).anyMatch(s -> s.equalsIgnoreCase(ROLE_ADMIN));
        }

        return false;
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
    @Override
    public Map<User, Integer> findUserWithCountOverdueOrder() {

        List<User> allUsersWithOrder = userRepository.findAllWithOrder();

        Map<User, Integer> userWithCountOverdueOrder = new LinkedHashMap<>();

        for (User user : allUsersWithOrder) {

            userWithCountOverdueOrder.put(user, user.getCountOverdueOrder());

        }

        return userWithCountOverdueOrder;
    }

    @Override
    @Modifying
    @Transactional
    public void deleteById(Long id) {

        userRepository.deleteConstraintFromUsersRolesTableByUserId(id);
        userRepository.deleteById(id);

    }

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

}