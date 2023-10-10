package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.services.MemberService;
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

    @PutMapping("/members/{id}")
    public Member updateMemberById(@PathVariable int id, @RequestBody Member member){
        return memberService.update(id, member);
    }

    @PatchMapping("/members/{id}")
    public Member updatePartOfMember(@PathVariable int id, @RequestBody Map<Object, Object> objectMap){
        return memberService.updatePartialy(id, objectMap);
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
