package ru.labs.coffer.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.PersonDto;

public interface PersonController {

    ResponseEntity<PersonDto> read(Long id);

    ResponseEntity<Void> update(PersonDto dto);

    ResponseEntity<Page<AdvertisementDto>> getAdvertisements(Pageable pageable);

    ResponseEntity<Void> delete();
}
