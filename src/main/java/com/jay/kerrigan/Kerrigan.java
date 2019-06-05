package com.jay.kerrigan;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ConfigurableApplicationContext;

import com.jay.kerrigan.common.config.KerriganBanner;
import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.master.KerriganMaster;
import com.jay.kerrigan.slave.KerriganSlave;

public class Kerrigan {

	public static final Class<? extends Kerrigan> role = null;
	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) throws Exception {
		args = new String[] { "master" };
		if (args == null || args.length != 1) {
			throw new Exception("Input args : master or slave");
		}
		if ("master".equalsIgnoreCase(args[0])) {
			setKerriganRole(KerriganMaster.class);
		} else if ("slave".equalsIgnoreCase(args[0])) {
			setKerriganRole(KerriganSlave.class);
		} else {
			throw new Exception("Input args : master or slave");
		}

		KerriganConfig.loadConfig();
		SpringApplication kerriganApp = new SpringApplication(role);
		kerriganApp.setBanner(new KerriganBanner());
		kerriganApp.setDefaultProperties(KerriganConfig.getAllConfig());
		applicationContext = kerriganApp.run();
	}

	private static void setKerriganRole(Class<? extends Kerrigan> kerriganRole) {
		try {
			Field roleField = Kerrigan.class.getField("role");
			roleField.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(roleField, roleField.getModifiers() & ~Modifier.FINAL);
			roleField.set(null, kerriganRole);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// Not going to happen
		}
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
