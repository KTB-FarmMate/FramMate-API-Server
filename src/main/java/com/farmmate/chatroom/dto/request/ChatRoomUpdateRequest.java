package com.farmmate.chatroom.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채팅방 수정 요청")
public record ChatRoomUpdateRequest(
	@Schema(description = "작물이 심어진 곳의 주소", defaultValue = "경기도 성남시 수정구 대왕판교로 815 (시흥동, 판교창조경제밸리)") String address,
	@Schema(description = "작물이 심어진 날짜", defaultValue = "2021-01-01") LocalDateTime plantedAt
) {
}
