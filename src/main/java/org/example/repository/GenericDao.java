package org.example.repository;

import org.example.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface GenericDao<T extends AbstractEntity, PK extends Serializable> {

    T create(T entity);

    void update(T entity);

    void delete(PK id);

    Set<T> getAll();

    Optional<T> get(PK id);

    void deleteAll();

}
