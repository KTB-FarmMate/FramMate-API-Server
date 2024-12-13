package com.farmmate.bookmark.dto.request;

import java.time.LocalDateTime;

public record BookmarkAddRequest(String question, String answer, LocalDateTime chattedAt) {
}
