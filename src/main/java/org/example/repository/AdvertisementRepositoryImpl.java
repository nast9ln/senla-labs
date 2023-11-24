package org.example.repository;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.example.AbstractDaoImpl;
import org.example.entity.Advertisement;
import org.example.entity.Advertisement_;
import org.example.entity.Person_;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AdvertisementRepositoryImpl extends AbstractDaoImpl<Advertisement, Long> implements AdvertisementRepository {
    public AdvertisementRepositoryImpl() {
        super(Advertisement.class);
    }

    @Override
    @Transactional
    public void deleteByPersonId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Advertisement> deleteQuery = criteriaBuilder.createCriteriaDelete(Advertisement.class);
        Root<Advertisement> advertisementRoot = deleteQuery.from(Advertisement.class);
        deleteQuery.where(criteriaBuilder.equal(advertisementRoot.get(Advertisement_.id), id));
        entityManager.createQuery(deleteQuery).executeUpdate();
    }

    @Override
    @Transactional
    public List<Advertisement> readByPersonId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advertisement> query = criteriaBuilder.createQuery(Advertisement.class);
        Root<Advertisement> advertisementRoot = query.from(Advertisement.class);

        Predicate predicate = criteriaBuilder.equal(advertisementRoot.get(Advertisement_.person).get(Person_.id), id);
        query.select(advertisementRoot).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public List<Advertisement> findAdvertisements(int pageNumber, int pageSize) {
        String jpql = "SELECT a FROM Advertisement a ORDER BY a.createdDate DESC";
        TypedQuery<Advertisement> query = entityManager.createQuery(jpql, Advertisement.class);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
    
    @Transactional
    public List<Advertisement> findAdvertisementsWithEntityGraph() {
        EntityGraph<Advertisement> entityGraph = entityManager.createEntityGraph(Advertisement.class);
        entityGraph.addAttributeNodes("person");

        String jpql = "SELECT a FROM Advertisement a";
        TypedQuery<Advertisement> query = entityManager.createQuery(jpql, Advertisement.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getResultList();
    }

}

