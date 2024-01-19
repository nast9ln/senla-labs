package ru.labs.coffer.service;

import ru.labs.coffer.dto.TopParamDto;

import java.util.List;

public interface TopParamService {
    List<Long> getCurrentTopParamIds();

    void update(TopParamDto dto);

    void updateIsTop();

    void extend(Integer extendTime, Long advId);
}
