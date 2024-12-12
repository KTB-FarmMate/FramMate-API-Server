package com.farmmate.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 등록 응답")
public record MemberRegisterResponse(
	@Schema(description = "회원 ID(식별자)", defaultValue = "member_1") String memberId
) {
}
