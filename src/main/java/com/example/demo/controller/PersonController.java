package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonController {

    PersonDto read(Long id);

    void update(PersonDto dto);

    void delete(Long id);
    Page<AdvertisementDto> getAdvertisements(Pageable pageable);

    }
