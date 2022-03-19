package com.ibm.geno.api.mentoring.model.repository;

import com.ibm.geno.api.mentoring.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {
}
