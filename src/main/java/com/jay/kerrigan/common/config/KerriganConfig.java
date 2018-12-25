package com.jay.kerrigan.common.config;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.jay.kerrigan.Kerrigan;
import com.jay.kerrigan.KerriganMaster;
import com.jay.kerrigan.KerriganSlave;

public class KerriganConfig {

	private KerriganConfig() {
	}

	private static class HolderClass {
		private final static KerriganConfig instance = new KerriganConfig();
	}

	private Map<String, Object> properties = Maps.newHashMap();

	public static void loadConfig(Class<? extends Kerrigan> clazz) throws Exception {
		loadDefaultConfig();

		String path = null;
		if (KerriganMaster.class == clazz) {
			path = "classpath:config/kerrigan-master.conf";
		} else if (KerriganSlave.class == clazz) {
			path = "classpath:config/kerrigan-slave.conf";
		} else {
			throw new Exception("Load Config File Error.");
		}

		List<String> lines = Files.readLines(ResourceUtils.getFile(path), Charset.forName("UTF-8"));

		for (int i = 0, cols = lines.size(); i < cols; i++) {
			String line = lines.get(i).trim();
			if (StringUtils.isBlank(line) || line.startsWith("#")) {
				continue;
			}
			int sepIndex = line.indexOf("=");
			if (1 > sepIndex) {
				throw new Exception("Config error [" + path + "]: at line " + i + " " + line);
			}
			String key = line.substring(0, sepIndex).trim();
			String value = line.substring(sepIndex + 1).trim();
			if (StringUtils.isAnyBlank(key, value)) {
				throw new Exception("Config error [" + path + "]: at line " + i + " " + line);
			}
			HolderClass.instance.properties.put(key, value);
		}
	}

	private static void loadDefaultConfig() {
		HolderClass.instance.properties.put("logging.config", "classpath:config/logback-config.xml");
		HolderClass.instance.properties.put("log.path", "log");
		HolderClass.instance.properties.put("log.file.pattern",
				Kerrigan.getKerriganRole().getSimpleName() + ".%d{yyyy-MM-dd}.log");
		HolderClass.instance.properties.put("server.tomcat.uri-encoding", "UTF-8");
		HolderClass.instance.properties.put("spring.aop.proxy-target-class", true);
		HolderClass.instance.properties.put("spring.jackson.date-format", "yyyy-MM-dd HH:mm:ss.SSS");
		HolderClass.instance.properties.put("spring.jackson.time-zone", "GMT+8");

		if (KerriganMaster.class == Kerrigan.getKerriganRole()) {
			loadMasterDefaultConfig();
		}

		if (KerriganSlave.class == Kerrigan.getKerriganRole()) {
			loadSlaveDefaultConfig();
		}
	}

	private static void loadMasterDefaultConfig() {
		HolderClass.instance.properties.put("server.port", 7000);
		HolderClass.instance.properties.put("mybatis.type-aliases-package", "com.jay.kerrigan.common.entity.mapper");
		HolderClass.instance.properties.put("mybatis.configuration.map-underscore-to-camel-case", true);
	}

	private static void loadSlaveDefaultConfig() {
		HolderClass.instance.properties.put("server.port", 7001);
	}

	public static Map<String, Object> getAllConfig() {
		return new HashMap<>(HolderClass.instance.properties);
	}

	public static String getConfig(String key) {
		return String.valueOf(HolderClass.instance.properties.get(key));
	}

	public static String getConfig(String key, String defaultValue) {
		Object property = HolderClass.instance.properties.get(key);
		return property == null ? defaultValue : String.valueOf(property);
	}

	public static int getConfig(String key, int defaultValue) {
		Object property = HolderClass.instance.properties.get(key);
		return property == null ? defaultValue : Integer.parseInt(String.valueOf(property));
	}

	public static long getConfig(String key, long defaultValue) {
		Object property = HolderClass.instance.properties.get(key);
		return property == null ? defaultValue : Long.parseLong(String.valueOf(property));
	}

	public static Map<String, Object> getConfigByPrefix(String prefix) {
		Map<String, Object> config = new HashMap<>();
		for (Entry<String, Object> entry : HolderClass.instance.properties.entrySet()) {
			String key = entry.getKey();
			if (key.startsWith(prefix)) {
				config.put(key, entry.getValue());
			}
		}
		return config;
	}
}
