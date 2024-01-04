package com.example.demo.service;

import com.example.demo.dto.TopParamDto;

import java.util.List;

public interface TopParamService {
    List<Long> getCurrentTopParamIds();

    void update(TopParamDto dto);

    void updateIsTop();

    void extend(Integer extendTime, Long advId);
}
