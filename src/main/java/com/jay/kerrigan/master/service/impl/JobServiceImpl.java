package com.jay.kerrigan.master.service.impl;

import org.springframework.stereotype.Service;

import com.jay.kerrigan.common.entity.table.Job;
import com.jay.kerrigan.master.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Override
	public boolean createJob(Job job) {
		return false;
	}

}
