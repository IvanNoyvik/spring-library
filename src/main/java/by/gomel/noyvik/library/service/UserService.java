package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;

public interface UserService {




    User login(Authenticate authenticate);

    boolean isExists(String login);

    User createNewUser(User user);

//    User registration(String login, String password, String name, String email);
//
//    Map<User, Integer> findUserWithCountOverdueOrder();
//
//    boolean changeStatus(Long userId, String status, int duration);
}
