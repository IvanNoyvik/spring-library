package by.gomel.noyvik.library.persistence.dao;

import by.gomel.noyvik.library.model.User;

import java.util.List;

public interface UserDao extends CrudDao<User> {


    List<Object[]> findAllWithOrder();

    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);


    User changeStatus(User user, String status, int duration);

    User changeStatus(Long userId, String status, int duration);

}
