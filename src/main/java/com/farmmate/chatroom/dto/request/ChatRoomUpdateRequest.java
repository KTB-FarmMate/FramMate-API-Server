package com.farmmate.chatroom.dto.request;

import java.time.LocalDateTime;

public record ChatRoomUpdateRequest(String address, LocalDateTime plantedAt) {
}
