package com.ibm.geno.api.mentoring.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "lastName", "age"})
public class PersonDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String name;

    @NotBlank(message = "last Name is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String lastName;

    @NotEmpty(message = "age is required")
    @Max(value = 125, message = "enter a valid age")
    @Positive(message = "age must be a positive number")
    private Integer age;

    @Valid
    private AddressDTO addressDTO;

    public PersonDTO(String name, String lastName, Integer age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonDTO(Long id, String name, String lastName, Integer age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
}
