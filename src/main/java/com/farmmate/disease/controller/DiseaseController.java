package com.farmmate.disease.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.disease.dto.response.DiseaseDetailResponse;
import com.farmmate.disease.service.DiseaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DiseaseController {
	private final DiseaseService diseaseService;

	@GetMapping("/pests")
	public DiseaseDetailResponse findPests(@RequestParam String cropName, @RequestParam String searchName) {
		return diseaseService.findPests(cropName, searchName);
	}
}
