package com.example.demo.service.impl;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Image;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Image image = imageRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Ad not found with id{0}", id));
        return imageMapper.toDto(image);
    }

    @Override
    public void update(Long id, ImageDto imageDto) {
        Image exImage = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found with id: {0}", id));
        Image newImage = imageMapper.toEntity(imageDto);
        imageMapper.update(exImage, newImage);
        imageRepository.save(exImage);
}

    @Override
    public void delete(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Image not found with id: {0}", id));
        image.setDeleted(true);
        imageRepository.save(image);
    }

    public void deleteByAdvertisementId(Long id){
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Ad not found whit {0}", id));
        List<Image> images = imageRepository.findAllByAdvertisementId(advertisement.getId()).stream()
                .peek(image -> image.setDeleted(true)).collect(Collectors.toList());
        imageRepository.saveAll(images);
    }



}
