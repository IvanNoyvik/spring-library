package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Book;

import java.io.InputStream;
import java.util.List;

public interface CrudService<T> {

    T findById(Long id);

    List<T> findAll();

    T save(T t);

    T update(T t);

    void deleteById(Long id);

}
