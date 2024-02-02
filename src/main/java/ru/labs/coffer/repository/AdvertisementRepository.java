package ru.labs.coffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.labs.coffer.entity.Advertisement;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>,
        PagingAndSortingRepository<Advertisement, Long> {
    List<Advertisement> findByPersonId(Long id);
    Page<Advertisement> findByPersonId(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM Advertisement WHERE person_id = :id", nativeQuery = true)
    Page<Advertisement> getHistoryByPersonId(@Param("id") Long id, Pageable pageable);

    Page<Advertisement> findAll(Specification<Advertisement> specification, Pageable pageable);

    Advertisement findByTopParamId(Long id);

    Optional<Advertisement> findByHeaderAndPersonId(String header, Long personId);
}

