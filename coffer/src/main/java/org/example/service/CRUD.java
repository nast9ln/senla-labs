package org.example.service;

import java.sql.SQLException;

public interface CRUD<T> {
    void create(T dto);

    T read(Long id);

    void update(T dto);

    void delete(Long id);
}
