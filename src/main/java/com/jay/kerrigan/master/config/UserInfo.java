package com.jay.kerrigan.master.config;

import java.util.HashMap;
import java.util.Map;

import com.jay.kerrigan.common.config.KerriganConfig;

public class UserInfo {

	private static final String USER_INFO_PREFIX = "system.username";

	private static final Map<String, String> USER_INFO = getUserInfo();

	private static Map<String, String> getUserInfo() {
		Map<String, Object> userInfoConfig = KerriganConfig.getConfigByPrefix(USER_INFO_PREFIX);
		Map<String, String> userInfo = new HashMap<>();
		userInfoConfig.forEach((k, v) -> {
			userInfo.put(k.substring(USER_INFO_PREFIX.length() + 1), String.valueOf(v));
		});
		if (userInfo.isEmpty()) {
			userInfo.put("kerrigan", "kerrigan");
		}
		return userInfo;
	}

	private UserInfo() {
	}

	public static final boolean checkUser(String userName, String password) {
		return password != null && password.equals(USER_INFO.get(userName));
	}
}
