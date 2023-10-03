package com.greger.inviduelluppg.repository;

import com.greger.inviduelluppg.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
