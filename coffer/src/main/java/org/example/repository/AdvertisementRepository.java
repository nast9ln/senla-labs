package org.example.repository;

import org.example.entity.Advertisement;
import org.example.service.CRUD;

import java.util.List;

public interface AdvertisementRepository extends CRUD<Advertisement> {
    void deleteByPersonId(Long id);

    List<Advertisement> readByPersonId(Long id);
}
