package com.jay.kerrigan;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ConfigurableApplicationContext;

import com.jay.kerrigan.common.config.KerriganBanner;
import com.jay.kerrigan.common.config.KerriganConfig;

public class Kerrigan {

	private static Class<? extends Kerrigan> kerriganRole;
	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) throws Exception {
		args = new String[] { "master" };
		if (args == null || args.length != 1) {
			throw new Exception("Input args : master or slave");
		}
		if ("master".equals(args[0])) {
			kerriganRole = KerriganMaster.class;
		} else if ("slave".equals(args[0])) {
			kerriganRole = KerriganSlave.class;
		} else {
			throw new Exception("Input args : master or slave");
		}

		KerriganConfig.loadConfig(kerriganRole);
		SpringApplication kerriganApp = new SpringApplication(kerriganRole);
		kerriganApp.setBanner(new KerriganBanner());
		kerriganApp.setDefaultProperties(KerriganConfig.getAllConfig());
		applicationContext = kerriganApp.run();
	}

	public static Class<? extends Kerrigan> getKerriganRole() {
		return kerriganRole;
	}

	public static <T> T getBean(Class<T> clazz) throws BeansException {
		if (applicationContext == null) {
			throw new ApplicationContextException("ApplicationContext is not available");
		}
		return applicationContext.getBean(clazz);
	}
}
