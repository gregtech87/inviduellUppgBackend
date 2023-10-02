package com.greger.inviduelluppg.Services;

import com.greger.inviduelluppg.Dao.MemberDAO;
import com.greger.inviduelluppg.Entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberDAO memberDAO;



    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Member findById(int id) {
        return null;
    }
}
