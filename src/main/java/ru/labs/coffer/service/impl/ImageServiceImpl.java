package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.labs.coffer.dto.ImageDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Image;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.mapper.ImageMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.ImageRepository;
import ru.labs.coffer.service.ImageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final AdvertisementRepository advertisementRepository;

    @Override
    public void create(ImageDto imageDto) {
        imageDto.setId(null);
        imageRepository.save(imageMapper.toEntity(imageDto));
    }

    @Override
    public ImageDto read(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ad not found with id{0}", id));
        return imageMapper.toDto(image);
    }

    @Override
    public void update(ImageDto dto) {
        Image exImage = imageRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Image not found with id: {0}", dto.getId()));
        Image newImage = imageMapper.toEntity(dto);
        imageMapper.update(exImage, newImage);
        imageRepository.save(exImage);
    }

    @Override
    public void delete(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found with id: {0}", id));
        image.setIsDeleted(true);
        imageRepository.save(image);
    }

    public void deleteByAdvertisementId(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ad not found whit {0}", id));
        List<Image> images = imageRepository.findAllByAdvertisementId(advertisement.getId()).stream()
                .peek(image -> image.setIsDeleted(true)).collect(Collectors.toList());
        imageRepository.saveAll(images);
    }
}
