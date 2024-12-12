package com.farmmate.chatroom.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "채팅방 등록 요청")
public record ChatRoomRegistrationRequest(
	@Schema(description = "채팅방 ID(식별자)", defaultValue = "thread_Q2NO6D1Jt8Stc9g8ds7LfOY6") String threadId,
	@Schema(description = "식물을 심은 곳의 주소", defaultValue = "경기도 성남시 수정구 대왕판교로 815 (시흥동, 판교창조경제밸리") String address,
	@Schema(description = "작물 ID(식별자)", defaultValue = "1") Integer cropId,
	@Schema(description = "심은 날짜", defaultValue = "2021-01-01") LocalDate plantedAt
) {
}
