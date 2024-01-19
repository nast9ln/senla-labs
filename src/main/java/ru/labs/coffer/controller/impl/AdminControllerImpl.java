package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> changePersonRole(@PathVariable Long id, @RequestBody Set<RoleDto> roles) {
        personService.updatePersonRole(id, roles);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/top/{advertisementId}")
    public ResponseEntity<Void> addTopParam(@PathVariable Long advertisementId, @RequestBody TopParamDto topParamDto) {
        advertisementService.addTopParam(advertisementId, topParamDto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/adv/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        advertisementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/top")
    public ResponseEntity<Void> changeTopParam(@RequestBody TopParamDto topParamDto) {
        topParamService.update(topParamDto);
        return ResponseEntity.ok().build();
    }
}
