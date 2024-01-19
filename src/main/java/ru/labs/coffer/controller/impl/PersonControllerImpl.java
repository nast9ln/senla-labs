package ru.labs.coffer.controller.impl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.PersonController;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.service.PersonService;

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
    @DeleteMapping
    public void delete() {
        logger.info("delete");
        personService.delete();
    }

    @Override
    @GetMapping("/adv")
    public Page<AdvertisementDto> getAdvertisements(Pageable pageable) {
        return personService.findAdvertisementByPersonId(pageable);
    }
}