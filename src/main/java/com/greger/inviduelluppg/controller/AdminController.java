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
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member getMember(@PathVariable int id){
        Member member = memberService.findById(id);
        if (member == null){
            throw new EntityNotFoundException("Medlem med id: " + id + " hittas ej!");
        }
        return member;
    }

    @PutMapping("/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updateMember(@RequestBody Member m){
        return memberService.save(m);
    }

    @PutMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updateMemberById(@PathVariable int id, @RequestBody Member member){
        return memberService.update(id, member);
    }

    @PatchMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member updatePartOfMember(@PathVariable int id, @RequestBody Map<Object, Object> objectMap){
        return memberService.updatePartialy(id, objectMap);
    }

    @PostMapping("/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member addMember(@RequestBody Member m) throws Exception {
        if (m.getId() > 0){
            m.setId(0);
            memberService.save(m);
            throw new EntityNotFoundException("Du ska inte ange id. Sparningen är genomförd men du har blivit tilldelad ett autogenererat id!");
        } else {
            return memberService.save(m);
        }
    }

    @DeleteMapping("/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public String deleteMember(@PathVariable int id){
        Member member = memberService.findById(id);
        if (member == null){
            throw new EntityNotFoundException("Medlem med id: " + id + " hittas ej!");
        }
        memberService.deleteById(id);
        return ("Medlem med id: " + id + " är raderad!");
    }
}
