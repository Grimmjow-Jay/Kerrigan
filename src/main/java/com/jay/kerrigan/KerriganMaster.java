package com.jay.kerrigan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.jay.kerrigan.common", "com.jay.kerrigan.master" })

@EnableScheduling
@Configuration
public class KerriganMaster extends Kerrigan {

}
