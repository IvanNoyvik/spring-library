package by.gomel.noyvik.library.persistence.repository;

import by.gomel.noyvik.library.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


    @Query("from User u left join fetch u.authenticate a left join fetch u.status left join fetch u.roles " +
            "where a.login = :login and a.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    User findByAuthenticateLogin(String login);

    @Query("select distinct u from User u left join fetch u.status " +
            "left join fetch u.authenticate left join fetch u.orders order by u.status.id desc")
    List<User> findAllWithOrder();

    @Modifying
    @Query(value = "DELETE FROM USERS_ROLES WHERE USERS_ID = :id", nativeQuery = true)
    void deleteConstraintFromUsersRolesTableByUserId(@Param("id") Long id);

    @Query("from User u join fetch u.authenticate left join fetch u.messages left join fetch u.status left join fetch u.orders" +
            " left join fetch u.roles where u.id = :userId")
    User findFullUserById(@Param("userId") Long userId);

}
