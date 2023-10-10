package com.greger.inviduelluppg.services;

import com.greger.inviduelluppg.dao.MemberDAO;
import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.entity.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public List<MemberDTO> findAllDto() {
        return memberDAO.findAllDto();
    }

    @Override
    public Member findById(Integer id) {
        return memberDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) { memberDAO.deleteById(id); }

    @Override
    @Transactional
    public Member update(int id, Member member) {
        return memberDAO.update(id, member);
    }

    @Override
    @Transactional
    public Member updatePartialy(int id, Map<Object, Object> objectMap) {
        return memberDAO.updatePartialy(id,objectMap);
    }
}
