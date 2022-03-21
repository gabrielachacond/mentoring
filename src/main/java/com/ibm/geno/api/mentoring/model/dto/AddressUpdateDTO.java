package com.ibm.geno.api.mentoring.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "street", "number", "floor", "department", "city", "province", "country"})
public class AddressUpdateDTO implements Serializable {

    private Long id;

    @Size(max = 200, message = "maximum characters is 200")
    private String street;

    @Positive(message = "number must be a positive number")
    private Integer number;

    @Positive(message = "floor must be a positive number")
    private Integer floor;

    @Size(max = 50, message = "maximum characters is 50")
    private String department;

    @Size(max = 50, message = "maximum characters is 50")
    private String city;

    @Size(max = 50, message = "maximum characters is 50")
    private String province;

    @Size(max = 50, message = "maximum characters is 50")
    private String country;

    public AddressUpdateDTO(String street, Integer number, Integer floor, String department, String city, String province, String country) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public AddressUpdateDTO(Long id, String street, Integer number, Integer floor, String department, String city, String province, String country) {
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

