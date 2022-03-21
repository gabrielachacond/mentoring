package com.ibm.geno.api.mentoring.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "street", "number", "floor", "department", "city", "province", "country"})
public class AddressDTO implements Serializable {

    private Long id;

    @NotBlank(message = "street is required")
    @Size(max = 200, message = "maximum characters is 200")
    private String street;

    @NotNull(message = "number is required")
    @Positive(message = "number must be a positive number")
    private Integer number;

    @NotNull(message = "floor is required")
    @Positive(message = "floor must be a positive number")
    private Integer floor;

    @NotBlank(message = "department is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String department;

    @NotBlank(message = "city is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String city;

    @NotBlank(message = "province is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String province;

    @NotBlank(message = "country is required")
    @Size(max = 50, message = "maximum characters is 50")
    private String country;

    public AddressDTO(String street, Integer number, Integer floor, String department, String city, String province, String country) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public AddressDTO(Long id, String street, Integer number, Integer floor, String department, String city, String province, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
    }
}

