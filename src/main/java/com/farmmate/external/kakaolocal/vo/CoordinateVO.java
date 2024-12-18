package com.farmmate.external.kakaolocal.vo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record CoordinateVO(double longitude, double latitude) {
	public static CoordinateVO from(String x, String y) {
		return new CoordinateVO(Double.parseDouble(x), Double.parseDouble(y));
	}

}
