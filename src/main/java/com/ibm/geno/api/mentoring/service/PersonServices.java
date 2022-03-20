package com.ibm.geno.api.mentoring.service;


import com.ibm.geno.api.mentoring.component.BusinessLogicExceptionComponent;
import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.model.entity.Person;
import com.ibm.geno.api.mentoring.model.mapper.CycleAvoidingMappingContext;
import com.ibm.geno.api.mentoring.model.mapper.PersonMapper;
import com.ibm.geno.api.mentoring.model.repository.AddressRepository;
import com.ibm.geno.api.mentoring.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


}
