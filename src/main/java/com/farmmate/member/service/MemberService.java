package com.farmmate.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmmate.member.dto.response.MemberRegisterResponse;
import com.farmmate.member.entity.Member;
import com.farmmate.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberRegisterResponse register() {
		Member newMember = Member.create();

		// Save new member to database
		Member savedMember = memberRepository.save(newMember);

		return new MemberRegisterResponse(savedMember.getId());
	}
}
