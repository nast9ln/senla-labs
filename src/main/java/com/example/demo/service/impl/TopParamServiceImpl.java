package com.example.demo.service.impl;

import com.example.demo.dto.TopParamDto;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.TopParam;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.TopParamRepository;
import com.example.demo.service.TopParamService;
import com.example.demo.service.mapper.TopParamMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TopParamServiceImpl implements TopParamService {
    private final TopParamRepository topParamRepository;
    private final TopParamMapper topParamMapper;
    private final AdvertisementRepository advertisementRepository;

    @Override
    public List<Long> getCurrentTopParamIds() {
        List<TopParam> topParams = topParamRepository.findByIsTopIsTrue();
        return topParams.stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TopParamDto dto) {
        TopParam newTopParam = topParamMapper.toEntity(dto);
        TopParam exTopParam = topParamRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Top param not found with id {0}", dto.getId()));
        topParamMapper.update(exTopParam, newTopParam);
        topParamRepository.save(exTopParam);
    }

    @Override
    public void updateIsTop() {
        List<TopParam> topParams = topParamRepository.findByIsTopIsTrue();
        for (TopParam topParam : topParams) {
            Instant currentTime = Instant.now();
            Instant endTime = topParam.getTimeTopStart().plusSeconds(topParam.getTimeInTop() * 60);

            if (currentTime.isAfter(endTime)) {
                topParam.setTop(false);
            }
        }
        topParamRepository.saveAll(topParams);

    }

    @Override
    public void extend(Integer extendTime, Long advId) {
        TopParam topParam = advertisementRepository.findById(advId)
                .orElseThrow(() -> new EntityNotFoundException("Advertisement was not found with id: {0}", advId))
                .getTopParam();
        if (topParam.isTop()) {
            Instant currentEndTime = topParam.getTimeTopStart().plusSeconds(topParam.getTimeInTop());
            topParam.setTimeInTop(topParam.getTimeInTop() + extendTime);
            topParamRepository.save(topParam);
        }
    }
}
