package com.example.demo.repository;

import com.example.demo.entity.TopParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopParamRepository extends JpaRepository<TopParam, Long> {
    List <TopParam> findByIsTopIsTrue();
}
