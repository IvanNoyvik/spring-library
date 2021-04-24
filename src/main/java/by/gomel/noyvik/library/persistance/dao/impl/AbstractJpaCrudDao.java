package by.gomel.noyvik.library.persistance.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.persistance.connection.JpaEntityManagerFactoryUtil;
import by.gomel.noyvik.library.persistance.dao.CrudDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractJpaCrudDao<T> implements CrudDao<T> {

    protected final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();


    public Class getGenericClass() {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T t;
        try {
            t = (T) entityManager.find(getGenericClass(), id);

        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "(findById abstract method)", e);
        } finally {
            entityManager.close();
        }
        return t;
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<T> list;
        try {
            list = entityManager.createQuery("from " + getGenericClass().getSimpleName(), getGenericClass()).getResultList();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "(findAll abstract method)", e);
        } finally {
            entityManager.close();
        }
        return list;
    }

    @Override
    public T save(T t) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "(save abstract method)", e);
        } finally {
            entityManager.close();
        }
        return t;
    }

    @Override
    public T update(T t) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            t = entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "(update abstract method)", e);
        } finally {
            entityManager.close();
        }
        return t;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T t;
        try {
            entityManager.getTransaction().begin();
            t = (T) entityManager.find(getGenericClass(), id);

            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "(deleteById abstract method)", e);
        } finally {
            entityManager.close();
        }
    }
}