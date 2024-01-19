package ru.labs.coffer.controller.impl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PersonDto> read(@PathVariable Long id) {
        logger.info("read");
        return ResponseEntity.ok(personService.read(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PersonDto dto) {
        logger.info("update");
        personService.update(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete() {
        logger.info("delete");
        personService.delete();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/adv")
    public ResponseEntity<Page<AdvertisementDto>> getAdvertisements(Pageable pageable) {
        return ResponseEntity.ok(personService.findAdvertisementByPersonId(pageable));
    }
}