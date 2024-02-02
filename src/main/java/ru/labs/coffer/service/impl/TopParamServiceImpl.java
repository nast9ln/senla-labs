package ru.labs.coffer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.entity.AbstractEntity;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.TopParam;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.mapper.TopParamMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.TopParamRepository;
import ru.labs.coffer.service.TopParamService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public void update(TopParamDto dto) {
        TopParam newTopParam = topParamMapper.toEntity(dto);
        TopParam exTopParam = topParamRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Top param not found with id {0}", dto.getId()));
        topParamMapper.update(exTopParam, newTopParam);
        topParamRepository.save(exTopParam);
    }

    @Override
    public void updateIsTop() {
        List<TopParam> topParams = topParamRepository.findAll();
        List<TopParam> forDeleteFromTop = new ArrayList<>();
        List<Advertisement> advertisements = new ArrayList<>();
        for (TopParam topParam : topParams) {
            Instant currentTime = Instant.now();
            Instant endTime = topParam.getTimeTopStart().plusSeconds(topParam.getTimeInTop() * 60);

            if (currentTime.isAfter(endTime)) {
                forDeleteFromTop.add(topParam);
                advertisements.add(advertisementRepository.findByTopParamId(topParam.getId()));
            }
        }
        advertisements.forEach(advertisement -> advertisement.setTopParam(null));
        topParamRepository.deleteAll(forDeleteFromTop);
    }

}
