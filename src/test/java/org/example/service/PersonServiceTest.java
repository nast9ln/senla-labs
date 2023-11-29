package org.example.service;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.example.service.impl.PersonServiceImpl;
import org.example.service.mapper.AdvertisementMapper;
import org.example.service.mapper.PersonMapper;
import org.example.util.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class PersonServiceTest {
    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private AdvertisementMapper advertisementMapper;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        // Arrange
        PersonDto personDto = new PersonDto();
        Person person = new Person();
        when(personMapper.toEntity(personDto)).thenReturn(person);
        when(personRepository.create(person)).thenReturn(person);

        PersonDto result = personService.create(personDto);

        verify(personRepository, times(1)).create(person);
    }


    @Test
    public void testRead() {
        Long personId = 1L;
        Person person = DataFactory.getPersonForTest(personId);
        when(personRepository.get(personId)).thenReturn(Optional.of(person));
        when(personMapper.toDto(person)).thenReturn(DataFactory.getPersonDtoForTest(personId));

        personService.read(personId);

        verify(personRepository, times(1)).get(personId);
        verify(advertisementRepository, times(1)).readByPersonId(personId);
    }

    @Test
    public void testUpdate() {
        PersonDto personDto = new PersonDto();
        Person existingPerson = new Person();
        when(personRepository.get(personDto.getId())).thenReturn(Optional.of(existingPerson));

        personService.update(personDto);

        verify(personRepository, times(1)).update(existingPerson);
    }

    @Test
    public void testDelete() {
        Long personId = 1L;
        Person person = new Person();
        when(personRepository.get(personId)).thenReturn(Optional.of(person));
        personService.delete(personId);

        verify(advertisementRepository, times(1)).deleteByPersonId(personId);
        verify(personRepository, times(1)).delete(personId);
    }
}