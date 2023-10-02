package com.greger.inviduelluppg.Dao;

import com.greger.inviduelluppg.Entity.Member;

import java.util.List;

public interface MemberDAO {
    List<Member> findAll();
    Member findById(int id);

}
