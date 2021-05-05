package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


//    List<Object[]> findAllWithOrder();

    @Query("from User u left join fetch u.authenticate a left join fetch u.status left join fetch u.roles " +
            "where a.login = :login and a.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    User findByAuthenticateLogin(String login);

    @Query("SELECT distinct u, o from User u left join fetch u.status " +
            "left join fetch u.authenticate left join TREAT (u.orders as Order ) o order by u.status.id desc")
    List<Object[]> findAllWithOrder();


//    User changeStatus(User user, String status, int duration);

//    User changeStatus(Long userId, String status, int duration);

}
