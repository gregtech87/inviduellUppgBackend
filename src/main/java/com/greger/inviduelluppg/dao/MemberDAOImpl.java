package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Address;
import com.greger.inviduelluppg.entity.AddressDTO;
import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.entity.MemberDTO;
import com.greger.inviduelluppg.services.AddressService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private EntityManager entityManager;
    private AddressService addressService;


    @Autowired
    public void MemberDAO(EntityManager manager) {
        entityManager = manager;
    }

    @Autowired
    public void AddressDAO(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public Member save(Member member) {

        Address memberAddress = member.getAddress();
        if (memberAddress.getId() > 0){
            member.setAddress(inspectIfAddressCameWithItsOwnId(member.getAddress()));
        }
        entityManager.merge(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> query = entityManager.createQuery("FROM Member", Member.class);
        return query.getResultList();
    }

    @Override
    public List<MemberDTO> findAllDto() {
        List<Member> memberList = findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(Member m: memberList){
            MemberDTO memberDTO = MemberToDto(m);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    @Override
    public Member findById(Integer id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Member member = entityManager.find(Member.class, id);
        int addressIdBeforeCheck = member.getAddress().getId();
        member = checkIfAddressExistInMoreMemberThanOneIfNotThenDeleteAddress(member);
        int addressIdAfterCheck = member.getAddress().getId();

        entityManager.remove(member);

        if (addressIdBeforeCheck != addressIdAfterCheck){
            addressService.deleteById(addressIdBeforeCheck);
        }
    }

    @Override
    public Member update(int id, Member member) {
        Member memberFromDb = findById(id);
        if (member == null){
            throw new RuntimeException("Medlem med id: " + id + " hittas ej!");
        }
        memberFromDb.setFirstName(member.getFirstName());
        memberFromDb.setLastName(member.getLastName());
        memberFromDb.setAddress(member.getAddress());
        memberFromDb.setEmail(member.getEmail());
        memberFromDb.setPhone(member.getPhone());
        memberFromDb.setDateOfBirth(member.getDateOfBirth());
        return save(memberFromDb);
    }

    @Override
    public Member updatePartialy(int id, Map<Object, Object> objectMap) {
        Member member = findById(id);
        if (member == null){
            throw new RuntimeException("Medlem med id: " + id + " hittas ej!");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Member.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field,member,value);
        });
        return save(member);
    }

    private MemberDTO MemberToDto(Member member) {
        MemberDTO memberDTO = new MemberDTO(
                member.getFirstName(),
                member.getFirstName(),
                member.getEmail(),
                member.getPhone(),
                AddressToDto(member.getAddress()));
        return memberDTO;
    }

    private AddressDTO AddressToDto(Address address) {
        AddressDTO addressDTO = new AddressDTO(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
        return addressDTO;
    }

    private Member checkIfAddressExistInMoreMemberThanOneIfNotThenDeleteAddress(Member member) {
        int id = member.getAddress().getId();
        List<Member> memberList = findAll();
        List<Address> addressList = new ArrayList<>();

        for(Member m: memberList){
            if(m.getAddress().getId() == id){
                addressList.add(m.getAddress());
            }
        }
        if(addressList.size() == 1){
            member.setAddress(new Address());
        }
        return member;
    }
    
    private Address inspectIfAddressCameWithItsOwnId(Address providedAddress) {
        List<Address> addressList = addressService.findAll();

        if(addressList.size() < providedAddress.getId()){
            providedAddress.setId(0);
        }else {
            providedAddress = addressService.findById(providedAddress.getId());
        }
        return providedAddress;
    }
}
