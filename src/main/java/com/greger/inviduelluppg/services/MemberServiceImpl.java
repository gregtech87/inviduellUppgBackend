package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.dao.MemberDAO;
import com.greger.inviduelluppg.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    @Transactional
    public Member save(Member member) {
        return memberDAO.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberDAO.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) { memberDAO.deleteById(id); }
}
