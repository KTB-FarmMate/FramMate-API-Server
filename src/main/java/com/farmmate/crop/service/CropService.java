package com.farmmate.crop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmmate.crop.dto.response.CropTypeResponse;
import com.farmmate.crop.entity.Crop;
import com.farmmate.crop.repository.CropRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CropService {
	private final CropRepository cropRepository;

	public List<CropTypeResponse> getAllCrops() {
		List<Crop> crops = cropRepository.findAll();

		return crops.stream()
			.map(CropTypeResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}
}
