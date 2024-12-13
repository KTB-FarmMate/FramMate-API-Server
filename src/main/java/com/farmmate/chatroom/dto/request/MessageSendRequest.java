package com.farmmate.chatroom.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "메시지 전송 요청")
public record MessageSendRequest(@Schema(description = "사용자 측 전송 메시지") String message) {
}
