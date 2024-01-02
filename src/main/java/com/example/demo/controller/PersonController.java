package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;

import java.util.List;

public interface PersonController {

    PersonDto read(Long id);

    void update(PersonDto dto);

    void delete(Long id);
}
