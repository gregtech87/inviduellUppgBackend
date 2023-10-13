package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Override
    public Address checkIfAddressExistsInDataBase(Address address) {
        Address addressFoundInDataBase = searchDB(address);
        if (addressFoundInDataBase != null) {
            return addressFoundInDataBase;
        }
        address.setId(0);
        return address;
    }

    private Address searchDB(Address address) {
        String street = address.getStreet();
        int postalCode = address.getPostalCode();
        String city = address.getCity();

        TypedQuery<Address> query = entityManager.createQuery(
                "FROM Address WHERE street = :street AND postalCode = :postalCode AND city = :city", Address.class);
        query.setParameter("street", street);
        query.setParameter("postalCode", postalCode);
        query.setParameter("city", city);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

