package com.farmmate.crop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.crop.dto.response.CropTypeResponse;
import com.farmmate.crop.service.CropService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crops")
@Tag(name = "작물", description = "작물 정보 관련 API")
public class CropController {
	private final CropService cropService;

	@GetMapping
	@Operation(summary = "작물 목록 조회", description = "서버에 등록되어있는 작물 목록을 조회합니다.")
	public List<CropTypeResponse> getCrops() {
		return cropService.getAllCrops();
	}
}
