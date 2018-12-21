package com.jay.kerrigan.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.entity.table.Job;
import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.JobService;

@RestController
@RequestMapping("/job")
@Validated
public class JobController {

//	@Autowired
//	private JobService jobService;
//
//	@RequestMapping("/create_job")
//	public ResponseModel<Boolean> createJob(Job job) {
//		return ResponseModel.success(jobService.createJob(job));
//	}
}
