package com.farmmate.external.weatherstation.type;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Region {
	CAPITAL("11B00000", List.of("서울", "인천", "경기도")),
	GANGWON_WEST("11D10000", List.of("강원도영서")),
	GANGWON_EAST("11D20000", List.of("강원도영동")),
	CHUNGCHEONGNAMDO("11C20000", List.of("대전", "세종", "충청남도")),
	CHUNGCHEONGBUKDO("11C10000", List.of("충청북도")),
	EOLLANAMDO("11F20000", List.of("광주", "전라남도")),
	JEOLLABUKDO("11F10000", List.of("전북자치도")),
	GYEONGSANGBUKDO("11H10000", List.of("대구", "경상북도")),
	GYEONGSANGNAMDO("11H20000", List.of("부산", "울산", "경상남도")),
	JEJUDO("11G00000", List.of("제주도"));

	private final String code;
	private final List<String> names;
}
