package com.farmmate.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.member.dto.response.MemberRegisterResponse;
import com.farmmate.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@PostMapping
	public MemberRegisterResponse register() {
		return memberService.register();
	}

}
