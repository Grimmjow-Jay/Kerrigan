package com.jay.kerrigan.master;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jay.kerrigan.Kerrigan;

@SpringBootApplication(scanBasePackages = { "com.jay.kerrigan.common", "com.jay.kerrigan.master" })
@EnableScheduling
@Configuration
public class KerriganMaster extends Kerrigan {

}
