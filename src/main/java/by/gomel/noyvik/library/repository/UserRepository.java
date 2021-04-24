package by.gomel.noyvik.library.repository;

import by.gomel.noyvik.library.bean.Role;
import by.gomel.noyvik.library.bean.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static by.gomel.noyvik.library.util.ErrorConstant.INVALID_USER_AUTHENTICATION_DATA;

@Repository
public class UserRepository implements CrudRepository<User> {

    private final static Map<Long, User> users = new HashMap<>();
    private final static AtomicLong ID = new AtomicLong(1);

    @PostConstruct
    public void init(){
        create(new User("admin", "admin", Role.ADMIN));
        create(new User("user", "user", Role.USER));
    }

    @Override
    public User create(User user) {

        long id = ID.getAndIncrement();
        user.setId(id);
        users.put(id, user);

        return getById(id);
    }

    @Override
    public User getById(long id) {
        return users.get(id);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public User getByLoginAndPassword(String login, String password) {
        return users.values().stream()
                .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(INVALID_USER_AUTHENTICATION_DATA));

    }

    public boolean isUserLoginExist(String login) {
        return users.values().stream()
                .anyMatch(u -> u.getLogin().equals(login));
    }

    @Override
    public User update(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public void delete(long id) {
        users.remove(id);
    }
}
