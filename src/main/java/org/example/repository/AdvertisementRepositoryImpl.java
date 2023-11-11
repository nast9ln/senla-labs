package org.example.repository;

import jakarta.persistence.criteria.*;
import org.example.AbstractDaoImpl;
import org.example.entity.Advertisement;
import org.example.entity.Advertisement_;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertisementRepositoryImpl extends AbstractDaoImpl<Advertisement, Long> implements AdvertisementRepository {

    public AdvertisementRepositoryImpl() {
        super(Advertisement.class);
    }

    @Override
    public void deleteByPersonId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Advertisement> deleteQuery = criteriaBuilder.createCriteriaDelete(Advertisement.class);
        Root<Advertisement> advertisementRoot = deleteQuery.from(Advertisement.class);
        deleteQuery.where(criteriaBuilder.equal(advertisementRoot.get(Advertisement_.id), id));
        entityManager.createQuery(deleteQuery).executeUpdate();
    }

    @Override
    public List<Advertisement> readByPersonId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advertisement> query = criteriaBuilder.createQuery(Advertisement.class);
        Root<Advertisement> advertisementRoot = query.from(Advertisement.class);

        Predicate predicate = criteriaBuilder.equal(advertisementRoot.get(Advertisement_.id), id);
        query.select(advertisementRoot).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
