package com.farmmate.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.member.dto.response.MemberRegisterResponse;
import com.farmmate.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원", description = "회원 관련 API")
public class MemberController {
	private final MemberService memberService;

	@Operation(summary = "회원 등록", description = "회원 등록 후, 등록된 회원 정보를 반환합니다.")
	@PostMapping
	public MemberRegisterResponse register() {
		return memberService.register();
	}

}
