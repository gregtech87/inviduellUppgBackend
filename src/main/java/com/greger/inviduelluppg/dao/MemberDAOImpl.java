package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private EntityManager entityManager;

    @Autowired
    public void MemberDAO(EntityManager manager) {
        entityManager = manager;
    }

    @Override
    public Member save(Member member) {
        entityManager.merge(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> query = entityManager.createQuery("FROM Member", Member.class);
        return query.getResultList();
    }

    @Override
    public Member findById(Integer id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Member member = entityManager.find(Member.class, id);
        entityManager.remove(member);
    }
}
