package com.ibm.geno.api.mentoring.service;


import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.model.entity.Person;
import com.ibm.geno.api.mentoring.model.mapper.CycleAvoidingMappingContext;
import com.ibm.geno.api.mentoring.model.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personServices")
public class PersonServices {

    @Autowired
    private CycleAvoidingMappingContext context;

    private PersonMapper personMapper = PersonMapper.MAPPER;

    public PersonDTO save(PersonDTO dto) {

        Person person = personMapper.toEntity(dto, context);

        return null;
    }


}
