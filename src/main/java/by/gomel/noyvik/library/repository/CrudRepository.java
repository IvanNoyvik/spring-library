package by.gomel.noyvik.library.repository;

public interface CrudRepository<T> {
    T create(T t);
    T getById(long id);
    T update(T t);
    void delete(long id);
}
