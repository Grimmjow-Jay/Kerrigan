package com.jay.kerrigan.common.util;

import java.util.UUID;

public class UUIDUtil {

	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}

	public static String randomUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
