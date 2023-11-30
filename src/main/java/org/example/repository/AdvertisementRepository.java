package org.example.repository;

import org.example.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


    List<Advertisement> findByPersonId(Long id);

}
