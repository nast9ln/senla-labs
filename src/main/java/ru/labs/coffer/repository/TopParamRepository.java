package ru.labs.coffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.labs.coffer.entity.TopParam;

import java.util.List;

public interface TopParamRepository extends JpaRepository<TopParam, Long> {
}
