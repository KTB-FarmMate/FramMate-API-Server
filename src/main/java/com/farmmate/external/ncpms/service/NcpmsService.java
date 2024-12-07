package com.farmmate.external.ncpms.service;

import static com.farmmate.external.type.ServiceCode.*;
import static com.farmmate.external.type.ServiceType.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.ncpms.dto.response.IntegratedSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NcpmsService {
	private static final String NCPMS_REQUEST_URL = "https://ncpms.rda.go.kr/npmsAPI/service";
	private static final WebClient webClient = WebClient.builder()
		.defaultHeaders(header -> header.set("Content-Type", "application/json"))
		.baseUrl(NCPMS_REQUEST_URL).build();
	@Value("${ncpms.apikey}")
	private String API_KEY;

	@SneakyThrows
	public void findFestAndDisease(String cropName, String searchName) {
		String responseBody = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.queryParam("searchName", searchName)
				.queryParam("apiKey", API_KEY)
				.queryParam("serviceCode", INTEGRATED_SEARCH.getCode())
				.queryParam("serviceType", JSON.getCode())
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.doOnError(error -> log.error("error: {}", error))
			.doOnSuccess(res -> log.info("병해충 통합정보 검색 성공"))
			.block();

		ObjectMapper objectMapper = new ObjectMapper();
		IntegratedSearchResponse response = objectMapper.readValue(responseBody, IntegratedSearchResponse.class);
	}
}
