package org.example.repository;

import org.example.GenericDao;
import org.example.entity.Advertisement;
import org.springframework.stereotype.Component;

import java.util.List;


public interface AdvertisementRepository extends GenericDao<Advertisement, Long> {
    void deleteByPersonId(Long id);

    List<Advertisement> readByPersonId(Long id);
}
