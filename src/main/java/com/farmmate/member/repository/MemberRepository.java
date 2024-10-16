package com.farmmate.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmmate.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
