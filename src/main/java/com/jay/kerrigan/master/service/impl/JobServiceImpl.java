package com.jay.kerrigan.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.kerrigan.common.entity.table.Job;
import com.jay.kerrigan.master.repository.JobRepository;
import com.jay.kerrigan.master.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Override
	public boolean createJob(Job job) {
		List<Job> findAll = jobRepository.findAll();
		System.out.println(findAll);
		jobRepository.save(job);
		return false;
	}

}
