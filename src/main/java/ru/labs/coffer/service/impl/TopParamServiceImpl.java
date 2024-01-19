package ru.labs.coffer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.entity.AbstractEntity;
import ru.labs.coffer.entity.TopParam;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.mapper.TopParamMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.TopParamRepository;
import ru.labs.coffer.service.TopParamService;

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

    public void create(TopParamDto topParamDto) {
        TopParam topParam = topParamMapper.toEntity(topParamDto);
        topParamRepository.save(topParam);
    }

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
