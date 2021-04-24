package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.persistance.connection.ProviderDao;
import by.gomel.noyvik.library.persistance.dao.CrudDao;
import by.gomel.noyvik.library.service.CrudService;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractCrudService<T> implements CrudService<T> {

    protected final ProviderDao PROVIDER_DAO = ProviderDao.getInstance();

    public Class<?> getGenericClass() {

        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T findById(Long id) {
        T t;
        CrudDao<?> crudDao = PROVIDER_DAO.getDaoByType(getGenericClass());
        if (crudDao != null) {

            t = (T) crudDao.findById(id);
        } else {
            throw new RuntimeException();
        }

        return t;
    }

    @Override
    public List<T> findAll() {

        List<T> t;
        CrudDao<?> crudDao = PROVIDER_DAO.getDaoByType(getGenericClass());
        if (crudDao != null) {

            t = (List<T>) crudDao.findAll();
        } else {
            throw new RuntimeException();
        }

        return t;
    }

    @Override
    public T save(T t) {

        CrudDao<T> crudDao = (CrudDao<T>) PROVIDER_DAO.getDaoByType(getGenericClass());
        if (crudDao != null) {

            t = (T) crudDao.save(t);
        } else {
            throw new RuntimeException();
        }

        return t;    }

    @Override
    public T update(T t) {
        CrudDao<T> crudDao = (CrudDao<T>) PROVIDER_DAO.getDaoByType(getGenericClass());
        if (crudDao != null) {

            t = (T) crudDao.update(t);
        } else {
            throw new RuntimeException();
        }

        return t;
    }

    @Override
    public void deleteById(Long id) {

        CrudDao<?> crudDao = PROVIDER_DAO.getDaoByType(getGenericClass());

        if (crudDao != null) {

            crudDao.deleteById(id);

        } else {
            throw new RuntimeException();
        }

    }
}
