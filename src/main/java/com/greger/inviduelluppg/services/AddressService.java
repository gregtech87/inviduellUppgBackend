package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.entity.Address;
import java.util.List;

public interface AddressService {
    Address save(Address address);
    List<Address> findAll();
    Address findById(Integer id);
    void deleteById(Integer id);
}
