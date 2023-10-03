package com.greger.inviduelluppg.dao;

import com.greger.inviduelluppg.entity.Address;
import com.greger.inviduelluppg.entity.Member;

import java.util.List;

public interface MemberDAO {
    Member save(Member member);
    List<Member> findAll();
    Member findById(Integer id);
    void deleteById(Integer id);
}
