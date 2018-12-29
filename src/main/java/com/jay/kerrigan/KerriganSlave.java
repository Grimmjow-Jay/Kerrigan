package com.jay.kerrigan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.jay.kerrigan.common",
		"com.jay.kerrigan.slave" }, exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@Configuration
public class KerriganSlave extends Kerrigan {

}
