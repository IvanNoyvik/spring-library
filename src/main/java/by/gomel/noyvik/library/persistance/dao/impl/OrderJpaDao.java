package by.gomel.noyvik.library.persistance.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.persistance.dao.OrderDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


public class OrderJpaDao extends AbstractJpaCrudDao<Order> implements OrderDao {

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {

            Order order = entityManager.find(Order.class, id);
            Book book = order.getBook();
            book.setQuantity(book.getQuantity() + 1);
            entityManager.merge(book);
            entityManager.remove(order);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoPartException(e.getMessage() + "deleteById from orderDao method", e);
        } finally {

            entityManager.close();
        }

    }

    @Override
    public List<Order> findByBookId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orders;
        try {
            orders = entityManager.createQuery("SELECT o from Order o " +
                    "join fetch o.book b " +
//                    "join fetch b.author " +
                    "join fetch o.user u join fetch u.authenticate " +
                    "where b.id = :id", Order.class)
                    .setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "findByBookId from orderDao method", e);
        } finally {
            entityManager.close();
        }

        return orders;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orders;
        try {
            orders = entityManager.createQuery("SELECT o from Order o join fetch o.book b " +
                    "join fetch b.author where o.user.id = :id", Order.class)
                    .setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        return orders;
    }

    @Override
    public List<Order> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orders;
        try {
            orders = entityManager.createQuery("SELECT o FROM Order o left join fetch o.book " +
                    "left join fetch o.user u left join fetch u.status left join fetch u.authenticate", Order.class).getResultList();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        return orders;
    }


    @Override
    public boolean findByBookAndUserId(Long bookId, Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.createQuery("SELECT o from Order o left join fetch o.book b " +
                    "left join fetch o.user u where u.id = :userId and b.id = :bookId", Order.class)
                    .setParameter("userId", userId).setParameter("bookId", bookId).getSingleResult();
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public List<Order> findAllOrdersByUserId(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orders;
        try {
            orders = entityManager.createQuery("SELECT o FROM Order o  " +
                    "where o.user.id = :userId", Order.class).setParameter("userId", userId).getResultList();


        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        return orders;

    }

    @Override
    public void removeAllOrder(Long userId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("delete Order o where o.user.id = :userId")
                    .setParameter("userId", userId).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }


}
