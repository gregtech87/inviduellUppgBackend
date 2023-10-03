package com.greger.inviduelluppg.controller;

import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.services.AddressService;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private MemberService memberService;
    private AddressService addressService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

//    public AdminController(AddressService addressService) {
//        this.addressService = addressService;
//    }

//    public AdminController(MemberService memberService, AddressService addressService) {
//        this.memberService = memberService;
//        this.addressService = addressService;
//    }
    @GetMapping("/members")
    public List<Member> findAll(){
        return memberService.findAll();
    }
}
