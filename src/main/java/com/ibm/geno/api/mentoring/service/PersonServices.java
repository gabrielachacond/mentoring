package com.ibm.geno.api.mentoring.service;


import com.ibm.geno.api.mentoring.component.BusinessLogicExceptionComponent;
import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.model.dto.PersonUpdateDTO;
import com.ibm.geno.api.mentoring.model.entity.Person;
import com.ibm.geno.api.mentoring.model.mapper.CycleAvoidingMappingContext;
import com.ibm.geno.api.mentoring.model.mapper.PersonMapper;
import com.ibm.geno.api.mentoring.model.repository.AddressRepository;
import com.ibm.geno.api.mentoring.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("personServices")
public class PersonServices {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private CycleAvoidingMappingContext context;

    private PersonMapper personMapper = PersonMapper.MAPPER;

    @Autowired
    private AddressRepository addressRepository;


    public PersonDTO save(PersonDTO dto) {
        //paso 1: busco la existencia del registro persona por dni
        Integer dni = dto.getDni();
        Person byDni = personRepository.findByDni(dni);

        // paso 2: si existe el dni, lanzo una excepcion de que existe el registro y termina el proceso
        if (byDni != null)
            throw logicExceptionComponent.getExceptionPersonAlreadyExists(byDni);

        // Paso 3: convierto el personDTO en Entity Person
        Person person = personMapper.toEntity(dto, context);

/* Este bloque es para que no guarde la misma direccion varias veces, para evitar redundancia de datos

        //paso 4: busco si existe la direccion en la base de datos
        Address addressByPerson = addressRepository.findByStreetAndNumberAndFloorAndDepartmentAndCityAndProvinceAndCountry(
                dto.getAddress().getStreet(),
                dto.getAddress().getNumber(),
                dto.getAddress().getFloor(),
                dto.getAddress().getDepartment(),
                dto.getAddress().getCity(),
                dto.getAddress().getProvince(),
                dto.getAddress().getCountry()
        );

        // paso 5: si existe la dirección, le asigno esa direcccion al Entity Persona
        if (addressByPerson != null)
            person.setAddress(addressByPerson);
*/

        // paso 6: guardo la información en la base de datos
        personRepository.save(person);

        // paso 7: convierto el entity person en personDTO con la información guardada en la BD
        PersonDTO personSaved = personMapper.toDto(person, context);

        // paso 8: retorno la información guardada
        return personSaved;
    }

    public void delete(Long id) {
        Optional<Person> byIdOptional = personRepository.findById(id);

        if (byIdOptional.isPresent()) {
            Person personToDelete = byIdOptional.get();
            personRepository.delete(personToDelete);
        } else {
            throw logicExceptionComponent.getExceptionEntityNotFound("Person", id);
        }
    }

    public PersonDTO update(PersonUpdateDTO dto, Long id) {
        Person personById = personRepository
                .findById(id) //busca por id
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Person", id)); // si no lo consigue, lanza esta excep

        // ver si el campo es distinto de null, y compara si el nombre del dto y el entity son distintos(false-negacion)
        if (dto.getName() != null && !personById.getName().equals(dto.getName())) {
            personById.setName(dto.getName());
        }
        if (dto.getLastName() != null && !personById.getLastName().equals(dto.getLastName())) {
            personById.setLastName(dto.getLastName());
        }
        if (dto.getDni() != null && !personById.getDni().equals(dto.getDni())) {
            personById.setDni(dto.getDni());
        }
        if (dto.getAge() != null && !personById.getAge().equals(dto.getAge())) {
            personById.setAge(dto.getAge());
        }
        if (dto.getAddress() != null) {
            if (dto.getAddress().getStreet() != null && !personById.getAddress().getStreet().equals(dto.getAddress().getStreet())) {
                personById.getAddress().setStreet(dto.getAddress().getStreet());
            }
            if (dto.getAddress().getNumber() != null && !personById.getAddress().getNumber().equals(dto.getAddress().getNumber())) {
                personById.getAddress().setNumber(dto.getAddress().getNumber());
            }
            if (dto.getAddress().getFloor() != null && !personById.getAddress().getFloor().equals(dto.getAddress().getFloor())) {
                personById.getAddress().setFloor(dto.getAddress().getFloor());
            }
            if (dto.getAddress().getDepartment() != null && !personById.getAddress().getDepartment().equals(dto.getAddress().getDepartment())) {
                personById.getAddress().setDepartment(dto.getAddress().getDepartment());
            }
            if (dto.getAddress().getCity() != null && !personById.getAddress().getCity().equals(dto.getAddress().getCity())) {
                personById.getAddress().setCity(dto.getAddress().getCity());
            }
            if (dto.getAddress().getProvince() != null && !personById.getAddress().getProvince().equals(dto.getAddress().getProvince())) {
                personById.getAddress().setProvince(dto.getAddress().getProvince());
            }
            if (dto.getAddress().getStreet() != null && !personById.getAddress().getCountry().equals(dto.getAddress().getCountry())) {
                personById.getAddress().setCountry(dto.getAddress().getCountry());
            }

        }

        //lo guardo en la base de datos
        personRepository.save(personById);

        //retorno del dto al entity
        PersonDTO personUpdated = personMapper.toDto(personById, context);

        //retorno la info guardada
        return personUpdated;

    }

}
