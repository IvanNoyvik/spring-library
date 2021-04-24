package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.User;

import java.util.Map;

public interface UserService extends CrudService<User> {




    User findByLoginAndPassword(String login, String password);

    boolean isExists(String login);

    User registration(String login, String password, String name, String email);

    Map<User, Integer> findUserWithCountOverdueOrder();

    boolean changeStatus(Long userId, String status, int duration);
}
