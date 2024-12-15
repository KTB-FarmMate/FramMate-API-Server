package com.farmmate.pest.service;

import org.springframework.stereotype.Service;

import com.farmmate.external.ncpms.service.NcpmsService;
import com.farmmate.external.ncpms.vo.PestDetailVo;
import com.farmmate.pest.dto.response.PestDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PestService {
	private final NcpmsService ncpmsService;

	public PestDetailResponse findPests(String cropName, String searchName) {
		PestDetailVo vo = ncpmsService.searchPestDetail(cropName, searchName);

		return PestDetailResponse.fromVo(vo);
	}
}
