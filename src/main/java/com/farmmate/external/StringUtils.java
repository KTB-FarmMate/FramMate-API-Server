package com.farmmate.external;

public final class StringUtils {
	public static boolean isNumber(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		try {
			Double.parseDouble(str);
			
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
