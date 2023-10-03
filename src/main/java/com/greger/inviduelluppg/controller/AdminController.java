package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Address;
import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.services.AddressService;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private MemberService memberService;
    private AddressService addressService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<Member> findAll(){
        return memberService.findAll();
    }
    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable int id){
        Member member = memberService.findById(id);
        if (member == null){
            throw new RuntimeException("Medlem med id: " + id + " hittas ej!");
        }
        return member;
    }

    @PutMapping("/members")
    public Member updateMember(@RequestBody Member m){
        return memberService.save(m);
    }

    @PostMapping("/members")
    public Member addMember(@RequestBody Member m){
        if (m.getId() > 0){
            throw new RuntimeException("Du kan inte ange id. Du har blivit tilldelad ett autogenererat id!");
        }
        m.setId(0);
        return memberService.save(m);
    }

    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable int id){
        Member member = memberService.findById(id);
        if (member == null){
            throw new RuntimeException("Medlem med id: " + id + " finns inte!");
        }
        memberService.deleteById(id);
        return ("Medlem med id: " + id + " är raderad!");
    }


}
