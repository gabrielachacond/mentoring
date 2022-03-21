package com.ibm.geno.api.mentoring.controller;

import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.model.dto.PersonUpdateDTO;
import com.ibm.geno.api.mentoring.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;


    @PostMapping({"", "/"})
    public ResponseEntity addPersonWithAddress(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {

        PersonDTO save = personServices.save(personDTO);
        return ResponseEntity
                .created(new URI("/person/" + save.getId()))
                .body(save);
    }

    @DeleteMapping({"/{id}", "/{id}/"}) // localhost:8081/person/1 y localhost:8081/person/1/;
    public ResponseEntity deletePerson(@PathVariable Long id) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping({"/{id}", "/{id}/"})
    public ResponseEntity updatePersonAndAddress(@RequestBody PersonUpdateDTO personUpdateDTO, @PathVariable Long id) {
        PersonDTO update = personServices.update(personUpdateDTO, id);
        return ResponseEntity.ok(update);
    }

}
