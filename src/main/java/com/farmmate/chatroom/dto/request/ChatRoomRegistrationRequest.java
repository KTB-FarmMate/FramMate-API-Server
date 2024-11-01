package com.farmmate.chatroom.dto.request;

import java.time.LocalDate;

public record ChatRoomRegistrationRequest(String threadId, String address, Integer cropId, LocalDate plantedAt) {
}
