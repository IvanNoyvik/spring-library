package by.gomel.noyvik.library.persistence.dao.impl;


import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.persistence.dao.RoleDao;

import javax.persistence.EntityManager;


public class RoleJpaDao extends AbstractJpaCrudDao<Role> implements RoleDao {

    private final String ADMIN = "Administrator";
    private final String USER = "User";

    @Override
    public Role getAdminRole() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Role role;
        try {
            role = (Role) entityManager.createQuery("SELECT r from Role r join fetch r.users where r.role = :role")
                    .setParameter("role", ADMIN).getSingleResult();
        } finally {
            entityManager.close();
        }
        return role;
    }

    @Override
    public Role getUserRole() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Role role;
        try {
            role = (Role) entityManager.createQuery("SELECT r from Role r join fetch r.users where r.role = :role")
                    .setParameter("role", USER).getSingleResult();
        } finally {
            entityManager.close();
        }
        return role;
    }
}
