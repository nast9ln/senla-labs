package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.SoftDeletableEntity;
import org.example.repository.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public abstract class AbstractDaoImpl<T extends SoftDeletableEntity, PK extends Serializable> implements GenericDao<T, PK> {

    private final Class<T> type;
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public Set<T> getAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(type);
        criteriaQuery.select(criteriaQuery.from(type));
        List<T> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        Set<T> resultSet = new HashSet<>(resultList);
        return resultSet;
    }


    @Override
    @Transactional
    public Optional<T> get(PK id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(PK id) {
        T entity = entityManager.find(type, id);
        entity.setDeleted(true);
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public T create(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteAll() {
        Set<T> entities = getAll();
        for (T entity : entities) {
            entity.setDeleted(true);
            entityManager.merge(entity);
        }
    }

}
