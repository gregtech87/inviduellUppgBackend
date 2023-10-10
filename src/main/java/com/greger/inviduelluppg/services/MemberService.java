package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.entity.MemberDTO;
import java.util.List;
import java.util.Map;

public interface MemberService {
    Member save(Member member);
    List<Member> findAll();
    List<MemberDTO> findAllDto();
    Member findById(Integer id);
    void deleteById(Integer id);
    Member updatePartialy(int id, Map<Object, Object> objectMap);
    Member update(int id, Member member);
}
