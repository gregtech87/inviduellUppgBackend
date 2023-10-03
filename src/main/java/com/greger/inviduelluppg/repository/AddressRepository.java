package com.greger.inviduelluppg.repository;

import com.greger.inviduelluppg.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
