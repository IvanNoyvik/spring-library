package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;

public interface UserService extends CrudService<User> {




    User login(Authenticate authenticate);

    boolean isExists(String login);

//    User registration(String login, String password, String name, String email);
//
//    Map<User, Integer> findUserWithCountOverdueOrder();
//
//    boolean changeStatus(Long userId, String status, int duration);
}
