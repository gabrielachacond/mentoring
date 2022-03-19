package com.ibm.geno.api.mentoring.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String street;

    @Column(name = "number")
    private Integer number;

    @Column(name = "floor")
    private Integer floor;

    @Column(nullable = false, length = 50)
    private String department;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String province;

    @Column(nullable = false, length = 50)
    private String country;

    @OneToOne(mappedBy = "address")
    private Person person;

    public Address(String street, Integer number, Integer floor, String department, String city, String province, String country) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Address(Long id) {
        this.id = id;
    }
}
