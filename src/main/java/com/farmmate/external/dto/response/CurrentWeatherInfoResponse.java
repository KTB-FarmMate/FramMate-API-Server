package com.farmmate.external.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeatherInfoResponse(
	Response response
) {
	@Override
	public String toString() {
		return response.toString();
	}

	public record Response(
		Header header,
		Body body
	) {
		@Override
		public String toString() {
			return header.toString() + ", " + body.toString();
		}

		public record Header(
			String resultCode,
			String resultMsg
		) {
			@Override
			public String toString() {
				return resultCode + ", " + resultMsg;
			}
		}

		public record Body(
			String dataType,
			Items items,
			int pageNo,
			int numOfRows,
			int totalCount
		) {
			@Override
			public String toString() {
				return dataType + ", " + items.toString() + ", " + pageNo + ", " + numOfRows + ", " + totalCount;
			}

			public record Items(
				@JsonProperty("item")
				List<Item> itemElements
			) {
				@Override
				public String toString() {
					return itemElements.toString();
				}

				public record Item(
					String baseDate,
					String baseTime,
					String category,
					int nx,
					int ny,
					String obsrValue
				) {
				}
			}
		}
	}
}
