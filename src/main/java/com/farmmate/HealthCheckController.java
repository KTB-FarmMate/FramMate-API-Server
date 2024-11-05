package com.farmmate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	public record HealthCheckResponse(String status) {
	}

	@GetMapping("/health")
	public ResponseEntity<HealthCheckResponse> checkHealth() {
		return ResponseEntity.ok().body(new HealthCheckResponse("OK"));
	}
}

