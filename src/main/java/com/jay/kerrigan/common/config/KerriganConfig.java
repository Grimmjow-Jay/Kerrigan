package com.jay.kerrigan.common.config;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import com.google.common.io.Files;
import com.jay.kerrigan.Kerrigan;
import com.jay.kerrigan.master.KerriganMaster;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KerriganConfig {

	private KerriganConfig() {
	}

	private static final KerriganProperties properties = new KerriganProperties();

	public static final void loadConfig() throws Exception {

		loadDefaultConfig();

		if (KerriganMaster.class == Kerrigan.getKerriganRole()) {
			loadMasterDefaultConfig();
			loadConfigFromFile("classpath:config/kerrigan-master.conf");
			properties.loadDefaultComplated = true;
			loadMasterConstantConfig();
		} else {
			loadSlaveDefaultConfig();
			loadConfigFromFile("classpath:config/kerrigan-slave.conf");
			properties.loadDefaultComplated = true;
			loadSlaveConstantConfig();
		}

		loadConstantConfig();
	}

	private static void loadConfigFromFile(String path) throws Exception {
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
			properties.put(key, value);
		}
	}

	/**
	 * Default config properties. Can be overwrite.
	 */
	private static void loadDefaultConfig() {
		properties.put("logging.config", "classpath:config/logback-config.xml");
		properties.put("log.path", "log");
		properties.put("log.file.pattern", Kerrigan.getKerriganRole().getSimpleName() + ".%d{yyyy-MM-dd}.log");
	}

	private static void loadMasterDefaultConfig() {
		properties.put("server.port", 7000);
	}

	private static void loadMasterConstantConfig() {
		properties.put("mybatis.type-aliases-package", "com.jay.kerrigan.common.entity.mapper");
		properties.put("mybatis.configuration.map-underscore-to-camel-case", true);
		properties.put("spring.thymeleaf.prefix", "classpath:templates/");
		properties.put("spring.thymeleaf.suffix", ".html");
		properties.put("spring.thymeleaf.mode", "HTML");
		properties.put("spring.thymeleaf.encoding", "UTF-8");
		properties.put("spring.thymeleaf.servlet.content-type", "text/html");
		properties.put("spring.thymeleaf.cache", false);
	}

	private static void loadSlaveDefaultConfig() {
		properties.put("server.port", 7001);
	}

	private static void loadSlaveConstantConfig() {

	}

	/**
	 * Constant config properties. Can't be overwrite.
	 */
	private static void loadConstantConfig() {
		properties.put("server.tomcat.uri-encoding", "UTF-8");
		properties.put("spring.aop.proxy-target-class", true);
		properties.put("spring.jackson.date-format", "yyyy-MM-dd HH:mm:ss.SSS");
		properties.put("spring.jackson.time-zone", "GMT+8");
		properties.put("spring.jackson.default-property-inclusion", "non-null");
	}

	public static Map<String, Object> getAllConfig() {
		return new HashMap<>(properties);
	}

	public static String getConfig(String key) {
		Object property = properties.get(key);
		return property == null ? null : property.toString();
	}

	public static String getConfig(String key, String defaultValue) {
		Object property = properties.get(key);
		return property == null ? defaultValue : property.toString();
	}

	public static int getConfig(String key, int defaultValue) {
		Object property = properties.get(key);
		try {
			return property == null ? defaultValue : Integer.parseInt(property.toString());
		} catch (NumberFormatException e) {
			log.error("Config property:[" + key + "] isn't a Integer. Return default value: [" + defaultValue + "]");
			return defaultValue;
		}
	}

	public static long getConfig(String key, long defaultValue) {
		Object property = properties.get(key);
		try {
			return property == null ? defaultValue : Long.parseLong(property.toString());
		} catch (NumberFormatException e) {
			log.error("Config property:[" + key + "] isn't a Long. Return default value: [" + defaultValue + "]");
			return defaultValue;
		}
	}

	public static boolean getConfig(String key, boolean defaultValue) {
		Object property = properties.get(key);
		try {
			return property == null ? defaultValue : Boolean.parseBoolean(property.toString());
		} catch (NumberFormatException e) {
			log.error("Config property:[" + key + "] isn't a Boolean. Return default value: [" + defaultValue + "]");
			return defaultValue;
		}
	}

	public static Map<String, Object> getConfigByPrefix(String prefix) {
		Map<String, Object> config = new HashMap<>();
		for (Entry<String, Object> entry : properties.entrySet()) {
			String key = entry.getKey();
			if (key.startsWith(prefix)) {
				config.put(key, entry.getValue());
			}
		}
		return config;
	}

	static class KerriganProperties extends HashMap<String, Object> {
		private static final long serialVersionUID = -8120810413140505541L;

		private boolean loadDefaultComplated = false;

		@Override
		public Object put(String key, Object value) {
			if (!loadDefaultComplated) {
				return super.put(key, value);
			}
			Object oldValue = get(key);
			if (oldValue != null && !oldValue.equals(value)) {
				System.err.format("Config property: [%s] cannot be overwrite. Default value is [%s]", key, value);
			}
			return super.put(key, value);
		}
	}
}
