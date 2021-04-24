package by.gomel.noyvik.library.persistance.dao.impl;


import by.gomel.noyvik.library.exception.DaoPartException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.persistance.dao.BookDao;
import by.gomel.noyvik.library.util.StringArrayEqual;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


public class BookJpaDao extends AbstractJpaCrudDao<Book> implements BookDao {


    @Override
    public Book findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Book book;
        try {
            book = entityManager.createQuery(
                    "SELECT b from Book b left join fetch b.author left join fetch b.genres where b.id = :id", Book.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "findById from bookDao method", e);
        } finally {
            entityManager.close();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Book> books;
        try {
            books = entityManager.createQuery("SELECT b from Book b left join fetch b.author", Book.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "findAll from bookDao method", e);
        } finally {
            entityManager.close();
        }
        return books;
    }

    @Override
    public byte[] findImageById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        byte[] image;
        try {
            Book book = entityManager.find(Book.class, id);
            image = book.getImage();
        } catch (Exception e) {
            throw new DaoPartException(e.getMessage() + "findImageById from bookDao method", e);
        } finally {
            entityManager.close();
        }
        return image;
    }

    @Override
    public void addImage(Long id, byte[] image) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Book book = entityManager.find(Book.class, id);
            book.setImage(image);
            entityManager.merge(book);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException("Add image method fail!", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public boolean findByTitleAndAuthor(String title, String author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.createQuery("from Book where title = :title and author.author = :author")
                    .setParameter("title", title).setParameter("author", author).getSingleResult();
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
    public Book save(Book book, String[] genreName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            if (genreName.length > 1) {
                List<Genre> genreList = entityManager.createQuery(
                        "select distinct g from Genre g left join fetch g.books", Genre.class).getResultList();
                for (Genre genre : genreList) {
                    for (String genreStr : genreName) {

                        if (genreStr.equals(genre.getGenre())) {
                            book.addGenre(genre);
                            break;
                        }
                    }
                }

            } else {

                Genre genre = entityManager.createQuery("from Genre g left join fetch g.books where g.genre = :genre", Genre.class)
                        .setParameter("genre", genreName[0]).getSingleResult();
                book.addGenre(genre);
            }
            entityManager.persist(book);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException();
        } finally {
            entityManager.close();
        }
        return book;
    }

    @Override
    public Book update(Long bookId, String title, String description, int quantity, String[] genres, String author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Book book;
        try {
            entityManager.getTransaction().begin();

            book = entityManager.createQuery("from Book b left join fetch b.genres left join fetch b.author where b.id = :id"
                    , Book.class).setParameter("id", bookId).getSingleResult();

            book.setQuantity(quantity);
            book.setDescription(description);
            book.setTitle(title);

            if (!book.getAuthor().getAuthor().equals(author)) {
                Author newAuthor = entityManager.createQuery("select a from Author a where a.author = :author"
                        , Author.class).setParameter("author", author).getSingleResult();
                book.setAuthor(newAuthor);
            }

            if (!StringArrayEqual.equals(genres, book.getGenres().stream().map(Genre::getGenre).toArray(String[]::new))) {
                while (!book.getGenres().isEmpty()) {
                    for (Genre genre : book.getGenres()) {
                        book.removeGenre(genre);
                        break;
                    }
                }
                List<Genre> genreList = entityManager.createQuery(
                        "select distinct g from Genre g left join fetch g.books", Genre.class).getResultList();

                for (Genre genre : genreList) {
                    for (String genreStr : genres) {
                        if (genreStr.equals(genre.getGenre())) {
                            book.addGenre(genre);
                            break;
                        }
                    }
                }
            }
            entityManager.merge(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoPartException(e);
        } finally {
            entityManager.close();
        }
        return book;
    }
}
