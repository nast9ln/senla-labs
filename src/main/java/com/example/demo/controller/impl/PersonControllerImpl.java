package com.example.demo.controller.impl;


import com.example.demo.controller.PersonController;
import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {
    private final PersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonControllerImpl.class);

    @Override
    @GetMapping("{id}")
    public PersonDto read(@PathVariable Long id) {
        logger.info("read");
        return personService.read(id);
    }

    @Override
    @PutMapping
    public void update(@RequestBody PersonDto dto) {
        logger.info("update");
        personService.update(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("delete");
        personService.delete(id);
    }

    @Override
    @GetMapping("/adv")
    public Page<AdvertisementDto> getAdvertisements(Pageable pageable) {
        return personService.findAdvertisementByPersonId(pageable);
    }
}