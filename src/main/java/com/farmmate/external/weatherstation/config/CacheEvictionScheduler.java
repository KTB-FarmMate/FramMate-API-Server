package com.farmmate.external.weatherstation.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CacheEvictionScheduler {
	@Scheduled(cron = "0 30 * * * *")
	@CacheEvict(value = "shortTermForeCast", allEntries = true)
	public void evictShortTermForeCastCache() {
		log.info("Cache Eviction: 단기 예보(3일) 캐시 삭제");
	}

	@Scheduled(cron = "0 0 * * * *")
	@CacheEvict(value = "currentWeatherInfo", allEntries = true)
	public void evictCurrentWeatherInfoCache() {
		log.info("Cache Eviction: 현재 날씨 캐시 삭제");
	}
}
