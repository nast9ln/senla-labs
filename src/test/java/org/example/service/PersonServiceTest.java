package org.example.service;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.entity.Role;
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
    private AdvertisementService advertisementService;

    @Mock
    private AdvertisementMapper advertisementMapper;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRead() {
        Long personId = 1L;
        Person person = DataFactory.getPersonForTest(personId);
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        when(personMapper.toDto(person)).thenReturn(DataFactory.getPersonDtoForTest(personId));

        personService.read(personId);

        verify(personRepository, times(1)).findById(personId);
        verify(advertisementRepository, times(1)).findByPersonId(personId);
    }

    @Test
    public void testUpdate() {
        PersonDto personDto = new PersonDto();
        Person existingPerson = new Person();
        when(personRepository.findById(personDto.getId())).thenReturn(Optional.of(existingPerson));

        personService.update(personDto);

        verify(personRepository, times(1)).save(existingPerson);
    }

    @Test
    public void testDelete() {
        Long personId = 1L;
        Person person = new Person();
        person.setId(personId);
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        when(advertisementService.deleteByPersonId(personId)).thenReturn(true);
        personService.delete(personId);

        verify(advertisementService, times(1)).deleteByPersonId(personId);
        verify(personRepository, times(1)).save(person);
    }
}