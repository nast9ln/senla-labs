package com.example.demo.controller.impl;

import com.example.demo.controller.AdminController;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.TopParamDto;
import com.example.demo.service.AdvertisementService;
import com.example.demo.service.CommentService;
import com.example.demo.service.PersonService;
import com.example.demo.service.TopParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {
    private final PersonService personService;
    private final CommentService commentService;
    private final AdvertisementService advertisementService;
    private final TopParamService topParamService;

    @PutMapping("/persons/{id}")
    public void changePersonRole(@PathVariable Long id, @RequestBody Set<RoleDto> roles) {
        personService.updatePersonRole(id, roles);
    }

    @PostMapping("/top/{advertisementId}")
    public void addTopParam(@PathVariable Long advertisementId, @RequestBody TopParamDto topParamDto) {
        advertisementService.addTopParam(advertisementId, topParamDto);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

    @DeleteMapping("/adv/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {
        advertisementService.delete(id);
    }

    @PutMapping("/top")
    public void changeTopParam(@RequestBody TopParamDto topParamDto) {
        topParamService.update(topParamDto);
    }
}
