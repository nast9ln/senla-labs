package org.example.service;

import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.exception.EntityNotFoundException;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.service.impl.AdvertisementServiceImpl;
import org.example.service.mapper.AdvertisementMapper;
import org.example.service.mapper.PersonMapper;
import org.example.util.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdvertisementServiceTest {

    @Mock
    private AdvertisementMapper advertisementDtoMapper;
    @Mock
    private AdvertisementRepository advertisementRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private AdvertisementServiceImpl advertisementService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        AdvertisementDto dto = DataFactory.getAdvertisementDtoForTest(1L);
        Advertisement advertisement = DataFactory.getAdvertisementForTest(1L);
        Person person = DataFactory.getPersonForTest(1L);
        advertisement.setId(1L);
        dto.setId(1L);
        advertisement.setPerson(person);
        when(advertisementDtoMapper.toEntity(dto)).thenReturn(advertisement);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));
        when(advertisementRepository.create(any(Advertisement.class))).thenReturn(advertisement);

        AdvertisementDto result = advertisementService.create(dto);

        verify(advertisementDtoMapper, times(1)).toEntity(dto);
        verify(personRepository, times(1)).findById(anyLong());
        verify(advertisementRepository, times(1)).create(advertisement);
    }

    @Test
    public void testRead() {
        long adId = 1L;
      //  Advertisement advertisement = new Advertisement();
        // AdvertisementDto expectedDto = new AdvertisementDto();

        Advertisement advertisement = DataFactory.getAdvertisementForTest(null);
        AdvertisementDto expectedDto = DataFactory.getAdvertisementDtoForTest(null);
        when(advertisementRepository.get(adId)).thenReturn(Optional.of(advertisement));
        when(advertisementDtoMapper.toDto(advertisement)).thenReturn(expectedDto);

        AdvertisementDto result = advertisementService.read(adId);

        verify(advertisementRepository, times(1)).get(adId);
        verify(advertisementDtoMapper, times(1)).toDto(advertisement);
    }

//    @Test(expected = EntityNotFoundException.class)
//    public void testReadWithEntityNotFoundException() {
//        Advertisement advertisement = DataFactory.getAdvertisementForTest(1L);
//        when(advertisementRepository.get(advertisement.getId())).thenReturn(null);
//
//        advertisementService.read(1L);
//
//        verify(advertisementRepository, times(1)).get(advertisement.getId());
//        verify(advertisementDtoMapper, never()).toDto(any());
//    }

//    @Test(expected = EntityNotFoundException.class)
//    public void testReadWithEntityNotFoundException() {
//        Advertisement advertisement = DataFactory.getAdvertisementForTest(1L);
//        when(advertisementRepository.get(advertisement.getId())).thenReturn(null);
//
//        advertisementService.read(advertisement.getId());
//
//        verify(advertisementRepository, times(1)).get(advertisement.getId());
//        verify(advertisementDtoMapper, never()).toDto(any());
//    }

    @Test
    public void testUpdate() {
        AdvertisementDto dto = new AdvertisementDto();
        Advertisement newAd = new Advertisement();
        Advertisement exAd = new Advertisement();
        when(advertisementDtoMapper.toEntity(dto)).thenReturn(newAd);
        when(advertisementRepository.get(dto.getId())).thenReturn(Optional.of(exAd));

        advertisementService.update(dto);

        verify(advertisementDtoMapper, times(1)).toEntity(dto);
        verify(advertisementRepository, times(1)).get(dto.getId());
        verify(advertisementDtoMapper, times(1)).update(exAd, newAd);
        verify(advertisementRepository, times(1)).update(exAd);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdateWithEntityNotFoundException() {
        AdvertisementDto dto = new AdvertisementDto();
        when(advertisementDtoMapper.toEntity(dto)).thenReturn(new Advertisement());
        when(advertisementRepository.get(dto.getId())).thenReturn(Optional.empty());

        advertisementService.update(dto);
        verify(advertisementDtoMapper, times(1)).toEntity(dto);
        verify(advertisementRepository, times(1)).get(dto.getId());
        verify(advertisementDtoMapper, never()).update(any(), any());
        verify(advertisementRepository, never()).update(any());
    }

    @Test
    public void testDelete() {
        long adId = 1L;
        Advertisement advertisement = new Advertisement();
        when(advertisementRepository.get(adId)).thenReturn(Optional.of(advertisement));

        advertisementService.delete(adId);

        verify(advertisementRepository, times(1)).get(adId);
        verify(advertisementRepository, times(1)).delete(adId);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteWithEntityNotFoundException() {
        long adId = 1L;
        when(advertisementRepository.get(adId)).thenReturn(Optional.empty());
        advertisementService.delete(1L);
        verify(advertisementRepository, times(1)).get(adId);
        verify(advertisementRepository, never()).delete(anyLong());
    }

}