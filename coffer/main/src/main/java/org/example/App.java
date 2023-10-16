package org.example;

import org.example.controller.PersonController;
import org.example.dto.PersonDto;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.example.service.impl.PersonServiceImpl;
import org.example.service.mapper.PersonDtoMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PersonService personService = new PersonServiceImpl(new PersonRepository(), new PersonDtoMapper());
        PersonController controller = new PersonController(personService);
        controller.execute(PersonDto.builder().id(1l).build());
        System.out.println( "Hello World!" );
    }
}
