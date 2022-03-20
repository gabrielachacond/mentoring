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

    @NotNull(message = "dni is required")
    @Positive(message = "dni must be a positive number")
    private Integer dni;

    @NotBlank(message = "name is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String name;

    @NotBlank(message = "lastName is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String lastName;

    @NotNull(message = "age is required")
    @Max(value = 125, message = "enter a valid age")
    @Positive(message = "age must be a positive number")
    private Integer age;

    @Valid
    @NotNull(message = "address is required")
    private AddressDTO address;
}
