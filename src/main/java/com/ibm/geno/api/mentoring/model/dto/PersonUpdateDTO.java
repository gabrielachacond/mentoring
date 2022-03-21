package com.ibm.geno.api.mentoring.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "dni", "name", "lastName", "age"})
public class PersonUpdateDTO implements Serializable {

    private Long id;

    @Positive(message = "dni must be a positive number")
    private Integer dni;

    @Size(max = 50, message = "maximum characters is 50")
    private String name;

    @Size(max = 50, message = "maximum characters is 50")
    private String lastName;

    @Max(value = 125, message = "enter a valid age")
    @Positive(message = "age must be a positive number")
    private Integer age;

    @Valid
    private AddressUpdateDTO address;
}
