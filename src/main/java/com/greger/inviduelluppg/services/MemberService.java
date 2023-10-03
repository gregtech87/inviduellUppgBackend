package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.entity.Member;

import java.util.List;

public interface MemberService {
    Member save(Member member);
    List<Member> findAll();
    Member findById(Integer id);
    void deleteById(Integer id);
}
