package com.greger.inviduelluppg.Services;

import com.greger.inviduelluppg.Entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Member findById(int id);
}
