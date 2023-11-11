package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private final Class<T> type;
    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public List<T> getAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(type);
        criteriaQuery.select(criteriaQuery.from(type));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<T> get(PK id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(PK id) {
        entityManager.remove(id);
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }
}
