package org.example.repository.impl;

import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Component

public class AdvertisementRepositoryImpl implements AdvertisementRepository {
    private Set<Advertisement> advertisements = new LinkedHashSet<>();
    private long firstId = 0;

    @Override
    public void create(Advertisement advertisement) {
        advertisement.setId(firstId++);
        advertisement.setCreatedDate(LocalDateTime.now());
        advertisements.add(advertisement);
    }

    @Override
    public Advertisement read(Long id) {
        return advertisements.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Advertisement dto) {

    }

    @Override
    public void delete(Long id) {
        advertisements.removeIf(a -> a.getId().equals(id));
    }
}
