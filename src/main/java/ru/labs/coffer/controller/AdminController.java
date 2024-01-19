package ru.labs.coffer.controller;

import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.dto.TopParamDto;

import java.util.Set;

public interface AdminController {
    ResponseEntity<Void> changePersonRole(Long id, Set<RoleDto> roles);

    ResponseEntity<Void> addTopParam(Long advertisementId, TopParamDto topParamDto);

    ResponseEntity<Void> deletePerson(Long id);

    ResponseEntity<Void> deleteComment(Long id);

    ResponseEntity<Void> deleteAdvertisement(Long id);

    ResponseEntity<Void> changeTopParam(TopParamDto topParamDto);
}
