package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.entity.MemberDTO;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypages")

public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    @PreAuthorize("hasRole('client_MEMBER')")
    public List<MemberDTO> findAll() {
        return memberService.findAllDto();
    }

    @PutMapping("/members")
    @PreAuthorize("hasRole('client_MEMBER')")
    public Member updateMember(@RequestBody Member m) {
        return memberService.save(m);
    }

    @PutMapping("/members/{id}")
    @PreAuthorize("hasRole('client_MEMBER')")
    public Member updateMember(@PathVariable int id, @RequestBody Member member) {
        return memberService.update(id, member);
    }
}
