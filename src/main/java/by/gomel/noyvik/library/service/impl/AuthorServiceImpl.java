package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.persistance.dao.AuthorDao;
import by.gomel.noyvik.library.service.AuthorService;

public class AuthorServiceImpl extends AbstractCrudService<Author> implements AuthorService {

    private final AuthorDao authorDao = PROVIDER_DAO.getAuthorDao();


    @Override
    public Author save(String authorStr) {
        if (!isExists(authorStr)) {
            Author author = new Author(authorStr);
            return authorDao.save(author);
        } else {
            throw new ServiceException();
        }
    }


    @Override
    public boolean isExists(String authorStr) {

        return authorDao.findByAuthor(authorStr) != null;
    }
}
