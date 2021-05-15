package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;

import java.util.List;

public interface UserService {


    User login(Authenticate authenticate);

    boolean isExists(String login);

    void createNewUser(User user);

    void updateUser(User userForUpdate);

    List<User> findAllUserWithOrder();

    void deleteById(Long id);

    void changeStatus(Long userId, String status, int duration);
}
