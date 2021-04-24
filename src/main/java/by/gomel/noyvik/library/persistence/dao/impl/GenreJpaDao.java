package by.gomel.noyvik.library.persistence.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.persistence.dao.GenreDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class GenreJpaDao extends AbstractJpaCrudDao<Genre> implements GenreDao {


    @Override
    public Genre findByGenre(String genreStr) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Genre genre;
        try {
            genre = entityManager.createQuery("from Genre where genre = :genre", Genre.class)
                    .setParameter("genre", genreStr).getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return genre;
    }
}
