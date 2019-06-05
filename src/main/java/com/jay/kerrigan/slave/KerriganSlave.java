package com.jay.kerrigan.slave;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jay.kerrigan.Kerrigan;

@SpringBootApplication(scanBasePackages = { "com.jay.kerrigan.common", "com.jay.kerrigan.slave" }, exclude = {
		DataSourceAutoConfiguration.class, QuartzAutoConfiguration.class, ThymeleafAutoConfiguration.class })
@EnableScheduling
@Configuration
public class KerriganSlave extends Kerrigan {

}
