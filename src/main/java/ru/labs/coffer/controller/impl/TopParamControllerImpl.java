package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.labs.coffer.controller.TopParamController;
import ru.labs.coffer.service.TopParamService;

import java.util.List;

@RestController
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopParamControllerImpl implements TopParamController {
    private final TopParamService topParamService;
    @Override
    @GetMapping
    public ResponseEntity<List<Long>> getCurrentTopIds() {
        return ResponseEntity.ok(topParamService.getCurrentTopParamIds());
    }
}
