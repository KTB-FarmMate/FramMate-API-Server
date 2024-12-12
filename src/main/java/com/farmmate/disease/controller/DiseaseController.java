package com.farmmate.disease.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.disease.dto.response.DiseaseDetailResponse;
import com.farmmate.disease.service.DiseaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "병충해", description = "병충해 정보 관련 API")
public class DiseaseController {
	private final DiseaseService diseaseService;

	@Operation(summary = "병충해 상세 조회", description = "사용자가 선택한 작물에 대한 병충해 상세 정보를 조회합니다.")
	@GetMapping("/diseases")
	public DiseaseDetailResponse findPests(@RequestParam String cropName, @RequestParam String searchName) {
		return diseaseService.findPests(cropName, searchName);
	}
}
