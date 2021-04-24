package by.gomel.noyvik.library.persistence.connection;

import by.gomel.noyvik.library.model.*;
import by.gomel.noyvik.library.persistence.dao.*;
import by.gomel.noyvik.library.persistence.dao.impl.*;

public final class ProviderDao {

    private static final ProviderDao INSTANCE = new ProviderDao();


    private final AuthenticateDao authenticateDao = new AuthenticateJpaDao();
    private final AuthorDao authorDao = new AuthorJpaDao();
    private final BookDao bookDao = new BookJpaDao();
    private final GenreDao genreDao = new GenreJpaDao();
    private final OrderDao orderDao = new OrderJpaDao();
    private final RoleDao roleDao = new RoleJpaDao();
    private final StatusDao statusDao = new StatusJpaDao();
    private final UserDao userDao = new UserJpaDao();



    private ProviderDao(){}

    public static ProviderDao getInstance(){
        return INSTANCE;
    }

    public AuthenticateDao getAuthenticateDao() {
        return authenticateDao;
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }


    public OrderDao getOrderDao() {
        return orderDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public StatusDao getStatusDao() {
        return statusDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }



    public <T> CrudDao<?> getDaoByType(Class<T> clazz){

        if (Authenticate.class.equals(clazz)) {
            return getAuthenticateDao();
        } else if (Author.class.equals(clazz)) {
            return getAuthorDao();
        } else if (Book.class.equals(clazz)) {
            return getBookDao();
        } else if (Genre.class.equals(clazz)) {
            return getGenreDao();
        } else if (Order.class.equals(clazz)) {
            return getOrderDao();
        } else if (Role.class.equals(clazz)) {
            return getRoleDao();
        } else if (Status.class.equals(clazz)) {
            return getStatusDao();
        } else if (User.class.equals(clazz)) {
            return getUserDao();
        } else {
            return null;
        }
    }
}
