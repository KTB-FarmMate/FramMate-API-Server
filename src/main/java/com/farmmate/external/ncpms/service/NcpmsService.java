package com.farmmate.external.ncpms.service;

import static com.farmmate.external.type.ServiceCode.*;
import static com.farmmate.external.type.ServiceType.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.ncpms.dto.response.DiseaseDetailResponse;
import com.farmmate.external.ncpms.dto.response.IntegratedSearchResponse;
import com.farmmate.external.ncpms.vo.DiseaseDetailVo;
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
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Value("${ncpms.apikey}")
	private String API_KEY;

	@SneakyThrows
	public DiseaseDetailVo findFestAndDisease(String cropName, String searchName) {
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

		IntegratedSearchResponse response = objectMapper.readValue(responseBody, IntegratedSearchResponse.class);

		String detailUrl = response.data()
			.diseaseDetails()
			.stream()
			.filter(diseaseDetail -> diseaseDetail.cropName().equals(cropName))
			.findFirst()
			.map(diseaseDetail -> diseaseDetail.detailUrl())
			.orElseThrow(() -> new RuntimeException("해당 작물의 병해충 정보가 없습니다."));

		responseBody = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.query(detailUrl)
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.doOnError(error -> log.error("error: {}", error))
			.doOnSuccess(res -> log.info("병해충 상세정보 조회 성공"))
			.block();

		DiseaseDetailResponse detailResponse = objectMapper.readValue(responseBody, DiseaseDetailResponse.class);

		log.info("disease detail vo: {}", DiseaseDetailVo.fromResponse(detailResponse));

		return DiseaseDetailVo.fromResponse(detailResponse);
	}
}
