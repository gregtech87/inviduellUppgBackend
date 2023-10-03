package com.greger.inviduelluppg;

import com.greger.inviduelluppg.dao.AddressDAO;
import com.greger.inviduelluppg.dao.MemberDAO;
import com.greger.inviduelluppg.entity.Address;
import com.greger.inviduelluppg.entity.Member;
import com.greger.inviduelluppg.services.AddressService;
import com.greger.inviduelluppg.services.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InviduellUppgBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InviduellUppgBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(MemberService memberService, AddressService addressService) {
        return runner -> {
            System.out.println("Hello World inifrån!");
            //JPA
            saveStudent(memberService, addressService);
//            readStudent(memberDAO);
//            saveMultipleStudent(studentDAO);
//            queryStudents(studentDAO);
//            queryStudentsSortByLastname(studentDAO);
//            queryStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAllStudents(studentDAO);
        };
    }

    private void readStudent(MemberService memberService) {
        int id = 1;
        Member m = memberService.findById(id);
        System.out.println("FFFFAAAAAAANNNN: " + m.getEmail());
        System.out.println("FFFFAAAAAAANNNN: " + m.getFirstName());
        System.out.println("FFFFAAAAAAANNNN: " + m.getId());
        System.out.println("FFFFAAAAAAANNNN: " + m.getLastName());
        System.out.println("FFFFAAAAAAANNNN: " + m.getPhone());
        System.out.println("FFFFAAAAAAANNNN: " + m.getDateOfBirth());
//        System.out.println("FFFFAAAAAAANNNN: " + m.getAddress());
//        System.out.println(m.getAddress());
    }

    private void saveStudent(MemberService memberService, AddressService addressService) {
        Address a = addressService.findById(1);
        System.out.println(a);
        System.out.println("Skapar en address");
        Address address = new Address("ryckipungvägen 2", 888888, "umeå");
        Address address2 = new Address("ryckipungvägen 2", 544444, "umeå");
//        address.setId(8);
//        address2.setId(9);
        System.out.println(address);
//        addressService.save(address);
//        addressService.save(address2);

//        List<Address> addressList = addressService.findAll();
//        for (Address aa: addressList){
//            System.out.println(aa);
//        }

        System.out.println("Skapar medlem");

//        Member member = new Member("first", "last" , 3,"email", 65656464 ,"DOB");
//        Member member2 = new Member("first2", "las2t" , 3,"emai2l", 644456464 ,"DOB");
        Member member = new Member("first", "last", "email", 654, "dob", null);
        Member member2 = new Member("first2", "last2", "email", 654, "dob", null);

        member.setAddress(address);
        member2.setAddress(address2);

        System.out.println(member);
        System.out.println(member2);



        System.out.println("Sparar studentobjekt till databasen:");
        memberService.save(member);
        memberService.save(member2);

        System.out.println("efter: " + member);
        System.out.println("efter: " + member2);

        List<Member> memberList2 = memberService.findAll();
        System.out.println("memberlist: " + memberList2);
        for(Member mm: memberList2){
            System.out.println(mm);
        }

    }


}
