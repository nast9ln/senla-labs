package ru.labs.coffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.labs.coffer.entity.Advertisement;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>,
        PagingAndSortingRepository<Advertisement, Long> {
    List<Advertisement> findByPersonId(Long id);

    @Query("SELECT a FROM Advertisement a LEFT JOIN TopParam t ON a.topParam.id = t.id ORDER BY " +
            "CASE WHEN t.id IS NOT NULL THEN 1 ELSE 2 END, a.createdDate DESC ")
    Page<Advertisement> findAllOrderedByTopAndCreatedDate(Pageable pageable);

    Page<Advertisement> findAllByCategoryIdOrderByTopParamIdDescCreatedDateDesc(Long id, Pageable pageable);

    Page<Advertisement> findAllByCostLessThanOrderByTopParamIdDescCreatedDateDesc(Integer cost, Pageable pageable);

    Page<Advertisement> findAllByCostGreaterThanOrderByTopParamIdDescCreatedDateDesc(Integer cost, Pageable pageable);

    Page<Advertisement> findByPersonId(Long id, Pageable pageable);


}
