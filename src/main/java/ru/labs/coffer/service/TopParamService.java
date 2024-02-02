package ru.labs.coffer.service;

import ru.labs.coffer.dto.TopParamDto;

import java.util.List;

public interface TopParamService {
    void update(TopParamDto dto);

    void updateIsTop();
}
