package com.example.demo.service;

public interface CRUD<T> {
    T create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(Long id);
}