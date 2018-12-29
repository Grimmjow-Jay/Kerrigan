package com.jay.kerrigan;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ConfigurableApplicationContext;

import com.jay.kerrigan.common.config.KerriganBanner;
import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.master.KerriganMaster;
import com.jay.kerrigan.slave.KerriganSlave;

public class Kerrigan {

	private static Class<? extends Kerrigan> kerriganRole;
	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) throws Exception {
		args = new String[] { "master" };
		if (args == null || args.length != 1) {
			throw new Exception("Input args : master or slave");
		}
		if ("master".equalsIgnoreCase(args[0])) {
			kerriganRole = KerriganMaster.class;
		} else if ("slave".equalsIgnoreCase(args[0])) {
			kerriganRole = KerriganSlave.class;
		} else {
			throw new Exception("Input args : master or slave");
		}

		KerriganConfig.loadConfig();
		SpringApplication kerriganApp = new SpringApplication(kerriganRole);
		kerriganApp.setBanner(new KerriganBanner());
		kerriganApp.setDefaultProperties(KerriganConfig.getAllConfig());
		applicationContext = kerriganApp.run();
	}

	public static Class<? extends Kerrigan> getKerriganRole() {
		return kerriganRole;
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		if (applicationContext == null) {
			throw new ApplicationContextException("ApplicationContext is not available");
		}
		return applicationContext.getBean(requiredType);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		if (applicationContext == null) {
			throw new ApplicationContextException("ApplicationContext is not available");
		}
		return applicationContext.getBean(name, requiredType);

	}
}
