package com.greger.inviduelluppg.Dao;

import com.greger.inviduelluppg.Entity.Address;
import com.greger.inviduelluppg.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO{

    private EntityManager entityManager;

    @Autowired
    public void MemberDAO (EntityManager manager){
        entityManager = manager;
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> query = entityManager.createQuery("FROM Member", Member.class);
        List<Member> members = query.getResultList();
//        for (Member m : members){
//            TypedQuery<Member> addressQuery = entityManager.createQuery("FROM Address where ", Member.class);
//            Address a = new Address();
//        }
        return members;
    }

    @Override
    public Member findById(int id) {
        Member member = entityManager.find(Member.class, id);
        return member;
    }
}
