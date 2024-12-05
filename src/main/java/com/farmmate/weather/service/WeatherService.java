package com.farmmate.weather.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.farmmate.external.service.KakaoLocalService;
import com.farmmate.external.service.WeatherStationService;
import com.farmmate.external.vo.CoordinateVO;
import com.farmmate.external.vo.NowCastVo;
import com.farmmate.external.vo.ShortTermForeCastVO;
import com.farmmate.weather.dto.response.CurrentWeatherInfoResponseDto;
import com.farmmate.weather.util.WeatherStationConverter;
import com.farmmate.weather.util.vo.GridVO;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherService {
	private final KakaoLocalService kakaoLocalService;
	private final WeatherStationService weatherStationService;

	public CurrentWeatherInfoResponseDto getCurrentWeatherInfo(@NotNull String address) {
		CoordinateVO coordinate = kakaoLocalService.convertAddress2Coordinate(address);
		GridVO gridVO = WeatherStationConverter.convertCoordinate2Grid(coordinate.latitude(), coordinate.longitude());

		NowCastVo vo = weatherStationService.getCurrentWeatherInfo(LocalDateTime.now(), gridVO.nx(),
			gridVO.ny());

		return CurrentWeatherInfoResponseDto.from(vo);
	}
}
