package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {


//    List<Object[]> findAllWithOrder();

    @Query("from User u join fetch u.authenticate a join fetch u.status where a.login = :login and a.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    User findByAuthenticateLogin(String login);


//    User changeStatus(User user, String status, int duration);

//    User changeStatus(Long userId, String status, int duration);

}
