package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Address;

import java.util.List;

public interface AddressDAO {
    Address save(Address address);
    List<Address> findAll();
    Address findById(Integer id);
    void deleteById(Integer id);
}
