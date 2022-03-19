package com.ibm.geno.api.mentoring.controller;

import com.ibm.geno.api.mentoring.model.dto.PersonDTO;
import com.ibm.geno.api.mentoring.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .created(new URI("/person/" + personDTO.getId()))
                .body(personDTO);
    }


}
