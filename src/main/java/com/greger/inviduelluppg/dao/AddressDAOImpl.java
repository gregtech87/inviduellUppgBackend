package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    private EntityManager entityManager;

    @Autowired
    public void AddressDAO(EntityManager manager) {
        entityManager = manager;
    }

    @Override
    public Address save(Address address) {
        entityManager.merge(address);
        return address;
    }

    @Override
    public List<Address> findAll() {
        TypedQuery<Address> query = entityManager.createQuery("FROM Address", Address.class);
        return query.getResultList();
    }

    @Override
    public Address findById(Integer id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Address address = entityManager.find(Address.class, id);
        entityManager.remove(address);
    }
}
