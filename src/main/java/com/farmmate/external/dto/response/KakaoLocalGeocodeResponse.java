package com.farmmate.external.dto.response;

import java.util.List;

public record KakaoLocalGeocodeResponse(List<Document> documents) {
	public record Document(String x, String y) {
	}
}
