package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.AdminController;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.service.AdvertisementService;
import ru.labs.coffer.service.CommentService;
import ru.labs.coffer.service.PersonService;
import ru.labs.coffer.service.TopParamService;

import java.util.Set;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {
    private final PersonService personService;
    private final CommentService commentService;
    private final AdvertisementService advertisementService;
    private final TopParamService topParamService;

    @Override
    @PutMapping("/persons/{id}")
    public void changePersonRole(@PathVariable Long id, @RequestBody Set<RoleDto> roles) {
        personService.updatePersonRole(id, roles);
    }

    @Override
    @PostMapping("/top/{advertisementId}")
    public void addTopParam(@PathVariable Long advertisementId, @RequestBody TopParamDto topParamDto) {
        advertisementService.addTopParam(advertisementId, topParamDto);
    }

    @Override
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @Override
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

    @Override
    @DeleteMapping("/adv/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {
        advertisementService.delete(id);
    }

    @Override
    @PutMapping("/top")
    public void changeTopParam(@RequestBody TopParamDto topParamDto) {
        topParamService.update(topParamDto);
    }
}
