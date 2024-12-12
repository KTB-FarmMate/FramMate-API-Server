package com.farmmate.external.kakaolocal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.kakaolocal.dto.response.KakaoLocalGeocodeResponse;
import com.farmmate.external.kakaolocal.vo.CoordinateVO;

@Service
public class KakaoLocalService {
	private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/address";
	@Value("${kakao.local.apikey}")
	private String API_KEY;

	private final WebClient webClient = WebClient.builder()
		.defaultHeaders(httpHeaders -> {
			httpHeaders.add("Content-Type", "application/json");
			httpHeaders.add("Authorization", "KakaoAK " + API_KEY);
		})
		.baseUrl(KAKAO_API_URL)
		.build();

	// 주소를 통해서 좌표 변환
	public CoordinateVO convertAddress2Coordinate(String address) {
		KakaoLocalGeocodeResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.queryParam("query", address)
				.build())
			.header("Content-Type", "application/json")
			.header("Authorization", "KakaoAK " + API_KEY)
			.retrieve()
			.bodyToMono(KakaoLocalGeocodeResponse.class)
			.block();

		KakaoLocalGeocodeResponse.Document document = response.documents().get(0);

		return CoordinateVO.from(document.x(), document.y());
	}
}
