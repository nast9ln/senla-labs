package org.example.repository.impl;

import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdvertisementRepositoryImpl implements AdvertisementRepository {
    private static final Map<Long, Advertisement> database = new HashMap<>();
    private long firstId = 0;

    @Override
    public void create(Advertisement advertisement) {
        advertisement.setId(firstId++);
        advertisement.setCreatedDate(LocalDateTime.now());
        database.put(advertisement.getId(), advertisement);
    }

    @Override
    public Advertisement read(Long id) {
        return database.get(id);
    }

    @Override
    public void update(Advertisement advertisement) {
        Advertisement exist = database.get(advertisement.getId());
        exist.setUserId(advertisement.getUserId());
        exist.setCategoryId(advertisement.getCategoryId());
        exist.setMainPictureId(advertisement.getMainPictureId());
        exist.setTopParamId(advertisement.getTopParamId());
        exist.setCreatedDate(advertisement.getCreatedDate());
        exist.setCost(advertisement.getCost());
        exist.setCity(advertisement.getCity());
        exist.setHeader(advertisement.getHeader());
        exist.setDescription(advertisement.getDescription());
        exist.setType(advertisement.getType());
        exist.setStatus(advertisement.getStatus());
        exist.setDeleted(advertisement.isDeleted());
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }
}
