package by.gomel.noyvik.library.persistence.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Status;
import by.gomel.noyvik.library.persistence.dao.StatusDao;

import javax.persistence.EntityManager;


public class StatusJpaDao extends AbstractJpaCrudDao<Status> implements StatusDao {


    private final String OK = "OK";
    private final String LOCKED = "Locked";
    private final String LIMITED = "Limited";


    @Override
    public Status getStatus(String statusStr) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status;
        try {
            status = entityManager.createQuery(
                    "SELECT s from Status s join fetch s.users where s.status = :status", Status.class)
                    .setParameter("status", statusStr).getSingleResult();
        } catch (Exception e) {
            throw new DaoPartException("getStatus method Exception", e);
        } finally {
            entityManager.close();
        }
        return status;
    }

    @Override
    public Status getOkStatus() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status;
        try {
            status = (Status) entityManager.createQuery("SELECT s from Status s join fetch s.users where s.status = :status")
                    .setParameter("status", OK).getSingleResult();
        } catch (Exception e) {
            throw new DaoPartException("getOkStatus Exception", e);
        } finally {
            entityManager.close();
        }
        return status;
    }

    @Override
    public Status getLockedStatus() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status;
        try {
            status = (Status) entityManager.createQuery("SELECT s from Status s join fetch s.users where s.status = :status")
                    .setParameter("status", LOCKED).getSingleResult();
        } catch (Exception e) {
            throw new DaoPartException("getLockedStatus Exception", e);
        } finally {
            entityManager.close();
        }
        return status;
    }

    @Override
    public Status getLimitedStatus() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status;
        try {
            status = (Status) entityManager.createQuery("SELECT s from Status s join fetch s.users where s.status = :status")
                    .setParameter("status", LIMITED).getSingleResult();
        } catch (Exception e) {
            throw new DaoPartException("getLimitedStatus Exception", e);
        } finally {
            entityManager.close();
        }
        return status;
    }
}
