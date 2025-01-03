package com.farmmate.external.vo;

public record CoordinateVO(double longitude, double latitude) {
	public static CoordinateVO from(String x, String y) {
		return new CoordinateVO(Double.parseDouble(x), Double.parseDouble(y));
	}

}
