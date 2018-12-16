package com.jay.kerrigan.common.config;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class KerriganEnv {

	public static final String KERRIGAN_HOME = getKerriganHome();

	public static final String TEMP_PATH = KERRIGAN_HOME + File.separator + "temp";

	public static final String STORE_PATH = KERRIGAN_HOME + File.separator + "store";

	public static final String PROJECTS_PATH = STORE_PATH + File.separator + "projects";

	private static final String getKerriganHome() {
		String kerriganHome = System.getenv("KERRIGAN_HOME");
		try {
			return kerriganHome == null
					? ResourceUtils.getFile(KerriganConfig.getConfig("kerrigan.home", "")).getAbsolutePath()
					: kerriganHome;
		} catch (FileNotFoundException e) {
			return new File("").getAbsolutePath();
		}
	}
}
