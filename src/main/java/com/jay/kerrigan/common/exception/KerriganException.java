package com.jay.kerrigan.common.exception;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class KerriganException extends RuntimeException {

	private static final long serialVersionUID = 7486627170003460534L;

	public KerriganException(String message) {
		super(message);
	}

	public static void assertCondition(boolean condition, String message) {
		if (condition) {
			throw new KerriganException(message);
		}
	}

	public static void assertNotNull(Object object, String message) {
		if (object == null) {
			throw new KerriganException(message);
		}
	}

	public static void assertNotBlank(CharSequence cs, String message) {
		if (StringUtils.isBlank(cs)) {
			throw new KerriganException(message);
		}
	}

	public static void assertNotEmpty(Map<?, ?> map, String message) {
		if (map == null || map.isEmpty()) {
			throw new KerriganException(message);
		}
	}

	public static void assertNotEmpty(Collection<?> collection, String message) {
		if (collection == null || collection.isEmpty()) {
			throw new KerriganException(message);
		}
	}
}
