package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.exceptions.EntityNotFoundException;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member getMember(@PathVariable int id) {
        Member member = memberService.findById(id);
        if (member == null) {
            throw new EntityNotFoundException("Member with id: " + id + " can not be found!");
        }
        return member;
    }

    @PutMapping("/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updateMember(@RequestBody Member m) {
        return memberService.save(m);
    }

    @PutMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updateMemberById(@PathVariable int id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @PatchMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updatePartOfMember(@PathVariable int id, @RequestBody Map<Object, Object> objectMap) {
        return memberService.updatePartialy(id, objectMap);
    }

    @PostMapping("/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member addMember(@RequestBody Member m) throws Exception {
        if (m.getId() > 0) {
            m.setId(0);
            memberService.save(m);
            throw new EntityNotFoundException("Do not assign id to member. member has been saved but with auto generated id!");
        } else {
            return memberService.save(m);
        }
    }

    @DeleteMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public String deleteMember(@PathVariable int id) {
        Member member = memberService.findById(id);
        if (member == null) {
            throw new EntityNotFoundException("Member with id: " + id + " can not be found!");
        }
        memberService.deleteById(id);
        return ("Member with id: " + id + " has been deleted!");
    }
}
