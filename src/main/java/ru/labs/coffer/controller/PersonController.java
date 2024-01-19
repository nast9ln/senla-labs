package ru.labs.coffer.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.PersonDto;

public interface PersonController {

    PersonDto read(Long id);

    void update(PersonDto dto);

    Page<AdvertisementDto> getAdvertisements(Pageable pageable);

    void delete();
}
