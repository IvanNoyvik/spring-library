package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;

import java.util.Map;

public interface UserService {




    User login(Authenticate authenticate);

    boolean isExists(String login);

    User createNewUser(User user);

    User updateUser(User userForUpdate);

    boolean isAdministrator(User user);

//    User registration(String login, String password, String name, String email);
//
    Map<User, Integer> findUserWithCountOverdueOrder();

    void deleteById(Long id);
//
//    boolean changeStatus(Long userId, String status, int duration);
}
