package com.farmmate.global.common.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmmate.global.error.exception.CustomException;
import com.farmmate.global.error.exception.ErrorCode;

public final class RepositoryUtils {
	public static <T, ID> T getReferenceOrThrow(JpaRepository<T, ID> repository, ID id, ErrorCode errorCode) {
		if (!repository.existsById(id)) {
			throw new CustomException(errorCode);
		}
		return repository.getReferenceById(id);
	}
}
