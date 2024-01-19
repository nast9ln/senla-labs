package ru.labs.coffer.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopParamController {
    ResponseEntity<List<Long>> getCurrentTopIds();
}
