package com.jay.kerrigan;

import org.springframework.boot.SpringApplication;

import com.jay.kerrigan.common.config.KerriganConfig;

public class Kerrigan {

	private static Class<? extends Kerrigan> kerriganRole;

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
		kerriganApp.setDefaultProperties(KerriganConfig.getAllConfig());
		kerriganApp.run();
	}

	public static Class<? extends Kerrigan> getKerriganRole() {
		return kerriganRole;
	}
}
