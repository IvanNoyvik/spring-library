//package by.gomel.noyvik.library.service.impl;
//
//import by.gomel.noyvik.library.exception.ServiceException;
//import by.gomel.noyvik.library.model.Genre;
//import by.gomel.noyvik.library.persistence.dao.GenreDao;
//import by.gomel.noyvik.library.service.GenreService;
//
//public class GenreServiceImpl extends AbstractCrudService<Genre> implements GenreService {
//
//    private final GenreDao genreDao = PROVIDER_DAO.getGenreDao();
//
//    @Override
//    public Genre save(String genreStr) {
//        if (!isExists(genreStr)) {
//            Genre genre = new Genre(genreStr);
//            return genreDao.save(genre);
//        } else {
//            throw new ServiceException();
//        }
//    }
//
//    @Override
//    public boolean isExists(String genreStr) {
//        return genreDao.findByGenre(genreStr) != null;
//    }
//}
