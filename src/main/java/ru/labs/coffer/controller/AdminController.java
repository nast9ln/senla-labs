package ru.labs.coffer.controller;

import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.dto.TopParamDto;

import java.util.Set;

public interface AdminController {
    void changePersonRole(Long id, Set<RoleDto> roles);

    void addTopParam(Long advertisementId, TopParamDto topParamDto);

    void deletePerson(Long id);

    void deleteComment(Long id);

    void deleteAdvertisement(Long id);

    void changeTopParam(TopParamDto topParamDto);
}
