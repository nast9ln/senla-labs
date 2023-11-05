package org.example.service;

public interface CRUD<T> {
    T create(T entity);

    T read(Long id);

    T update(T entity);

    void delete(Long id);
}
