package org.example.service;

import java.sql.SQLException;

public interface CRUD<T> {
    T create(T entity);

    T read(Long id);

    T update(T entity);

    void delete(Long id);
}
