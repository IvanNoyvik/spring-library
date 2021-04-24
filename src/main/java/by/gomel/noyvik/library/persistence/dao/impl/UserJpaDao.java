package by.gomel.noyvik.library.persistence.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.model.Status;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.persistence.dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

public class UserJpaDao extends AbstractJpaCrudDao<User> implements UserDao {

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.createNativeQuery("DELETE from USERS_ROLES where USERS_ID = :userId")
                    .setParameter("userId", user.getId()).executeUpdate();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users;
        try {
            users = entityManager.createQuery("SELECT u from User u left join fetch u.status " +
                    "left join fetch u.authenticate order by u.status.id desc", User.class).getResultList();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public List<Object[]> findAllWithOrder() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Object[]> users;
        try {
            users = entityManager.createQuery("SELECT distinct u, o from User u left join fetch u.status " +
                    "left join fetch u.authenticate left join TREAT (u.orders as Order ) o order by u.status.id desc").getResultList();

        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return users;
    }


    @Override
    public User save(User user) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Role role = entityManager.find(Role.class, 2L);
            user.addRole(role);
            entityManager.persist(user);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user;
        try {
            user = (User) entityManager.createQuery("SELECT u from User u left join fetch u.authenticate a " +
                    "left join fetch u.orders left join fetch u.roles left join fetch u.status left join fetch u.messages " +
                    "where a.login = :login and a.password = :password")
                    .setParameter("login", login).setParameter("password", password).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        return user;

    }

    @Override
    public User findByLogin(String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user;
        try {
            user = (User) entityManager.createQuery("SELECT u from User u join fetch u.authenticate a " +
                    "where a.login = :login")
                    .setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public User changeStatus(User user, String status, int duration) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            LocalDate unlockedDate = LocalDate.now().plusDays(duration);
            user.getAuthenticate().setUnlockedDate(unlockedDate);

            Status newStatus = entityManager.createQuery(
                    "SELECT s from Status s left join fetch s.users u left join fetch u.authenticate " +
                            "where s.status = :status", Status.class)
                    .setParameter("status", status).getSingleResult();
            user.setStatus(newStatus);
            entityManager.merge(user);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoPartException(e);
        } finally {
            entityManager.close();
        }

        return user;
    }

    @Override
    public User changeStatus(Long userId, String status, int duration) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user;
        try {

            entityManager.getTransaction().begin();

            user = entityManager.createQuery("from User u left join fetch u.authenticate " +
                    " where u.id = :id", User.class)
                    .setParameter("id", userId).getSingleResult();

            LocalDate unlockedDate = LocalDate.now().plusDays(duration);
            user.getAuthenticate().setUnlockedDate(unlockedDate);

            Status newStatus = entityManager.createQuery(
                    "SELECT s from Status s left join fetch s.users u left join fetch u.authenticate " +
                            "where s.status = :status", Status.class)
                    .setParameter("status", status).getSingleResult();
            user.setStatus(newStatus);
            entityManager.merge(user);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoPartException(e);
        } finally {
            entityManager.close();
        }
        return user;
    }


    @Override
    public User findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user;
        try {
            user = (User) entityManager.createQuery("SELECT u from User u " +
                    "join fetch u.authenticate left join fetch u.status left join fetch u.orders where u.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return user;
    }
}
