package com.muates.memberservice.repository;

import com.muates.memberservice.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllById(Iterable<Long> ids);
}
