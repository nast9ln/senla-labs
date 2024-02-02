package ru.labs.coffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.labs.coffer.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByAdvertisementId(Long id);
}
