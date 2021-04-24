package by.gomel.noyvik.library.persistance.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.persistance.dao.AuthorDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class AuthorJpaDao extends AbstractJpaCrudDao<Author> implements AuthorDao {

    @Override
    public Author findByAuthor(String authorStr) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Author author;
        try {
            author = entityManager.createQuery("from Author where author = :author", Author.class)
                    .setParameter("author", authorStr).getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "findByAuthor method Exception", e);
        } finally {
            entityManager.close();
        }
        return author;
    }
}
