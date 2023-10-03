package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypages")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService membService){
        memberService = membService;
    }

    @GetMapping("/members")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable int id){
        Member member = memberService.findById(id);
        if(member == null){
            throw new RuntimeException("Member med id: " + id + " finns inte.");
        }
        return member;
    }



}
