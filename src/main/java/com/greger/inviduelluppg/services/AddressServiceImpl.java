package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.dao.AddressDAO;
import com.greger.inviduelluppg.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressDAO addressDAO;

    @Autowired
    public AddressServiceImpl(AddressDAO addressDAO){ this.addressDAO = addressDAO; }

    @Override
    @Transactional
    public Address save(Address address) {return addressDAO.save(address); }

    @Override
    public List<Address> findAll() {
       return addressDAO.findAll();
    }

    @Override
    public Address findById(Integer id) {
        return addressDAO.findById(id);
    }


    @Override
    @Transactional
    public void deleteById(Integer id) {
       addressDAO.deleteById(id);
    }
}
