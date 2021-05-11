package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.model.Status;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.repository.*;
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

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;
    private final MessageRepository messageRepository;


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

        User user = userRepository.findByAuthenticateLogin(login);
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
    @Override
    @Modifying
    @Transactional
    public boolean changeStatus(Long userId, String status, int duration) {
//todo show Anton
        try {

            User user = userRepository.findFullUserById(userId);

            if (!user.getStatus().getStatus().equals(status)) {

                if (status.equals(LOCKED) && !user.getOrders().isEmpty()) {
                    orderRepository.removeAllByUserId(userId);
                    user.getOrders().clear();
                }

                if (status.equals(OK) && !user.getMessages().isEmpty()) {
                    messageRepository.removeAllByUserId(userId);
                    user.getMessages().clear();
                }

                LocalDate unlockedDate = LocalDate.now().plusDays(duration);
                Status newStatus = statusRepository.findByStatus(status);
                user.getAuthenticate().setUnlockedDate(unlockedDate);
                user.setStatus(newStatus);

                userRepository.save(user);

            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }


        return true;

    }

}