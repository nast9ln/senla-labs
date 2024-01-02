package com.example.demo.controller.impl;

import com.example.demo.controller.TopParamController;
import com.example.demo.service.TopParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopParamControllerImpl implements TopParamController {
    private final TopParamService topParamService;
    @Override
    @GetMapping
    public List<Long> getCurrentTopIds() {
        return topParamService.getCurrentTopParamIds();
    }
}
