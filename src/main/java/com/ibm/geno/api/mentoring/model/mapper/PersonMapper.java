package com.ibm.geno.api.mentoring.model.mapper;

import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper extends DataCycleMapper<PersonDTO, Person> {

    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);
}
