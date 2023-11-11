package org.example;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, PK extends Serializable> {

    T create(T entity);

    void update(T entity);

    void delete(PK id);

    List<T> getAll();

    Optional<T> get(PK id);

}
