package io.mbab.sda.groupproject.repository;

import java.util.List;

public interface CrudRepository2<T, ID> {

    List<T> getAll();

    T findById(ID id);

    T findByTitle(String Tittle);

    T create(T entity);

    T update(T entity);

    T save (T entity);

    void delete(ID id);
}
