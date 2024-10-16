package com.farmmate.crop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farmmate.crop.dto.response.CropTypeResponse;
import com.farmmate.crop.service.CropService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/crops")
@RequiredArgsConstructor

public class CropController {
	private final CropService cropService;

	@GetMapping
	public List<CropTypeResponse> getCrops() {
		return cropService.getAllCrops();
	}
}
