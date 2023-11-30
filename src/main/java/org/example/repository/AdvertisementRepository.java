package org.example.repository;

import org.example.entity.Advertisement;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AdvertisementRepository extends GenericDao<Advertisement, Long> {
    void deleteByPersonId(Long id);
    List<Advertisement> readByPersonId(Long id);
    List<Advertisement> findAdvertisements(int pageNumber, int pageSize);
     List<Advertisement> findAdvertisementsWithEntityGraph();

    }
