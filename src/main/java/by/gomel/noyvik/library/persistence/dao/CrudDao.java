package by.gomel.noyvik.library.persistence.dao;

import java.util.List;

public interface CrudDao<T> {


    T findById(Long id);

    List<T> findAll();

    T save(T t);

    T update(T t);

    void deleteById(Long id);
}
