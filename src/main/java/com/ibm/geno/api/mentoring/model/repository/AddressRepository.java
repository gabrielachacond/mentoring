package com.ibm.geno.api.mentoring.model.repository;

import com.ibm.geno.api.mentoring.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
