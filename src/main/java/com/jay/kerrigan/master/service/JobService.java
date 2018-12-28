package com.jay.kerrigan.master.service;

import java.util.List;

import com.jay.kerrigan.common.entity.table.Job;

public interface JobService {

	List<Job> fetchAll();
	
	boolean createJob(Job job);

}
