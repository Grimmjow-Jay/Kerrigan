package com.jay.kerrigan.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jay.kerrigan.common.entity.table.Flow;
import com.jay.kerrigan.common.entity.table.Job;
import com.jay.kerrigan.common.entity.table.Project;
import com.jay.kerrigan.master.mapper.JobMapper;
import com.jay.kerrigan.master.service.JobService;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobMapper jobMapper;

	@Override
	public List<Job> fetchAll() {
		List<Job> fetchAll = jobMapper.fetchAll();
		for (Job job : fetchAll) {
			Project project = job.getProject();
			List<Flow> flows = job.getFlows();
			System.out.println(project);
			System.out.println(flows);
		}
		return fetchAll;
	}

	@Override
	public boolean createJob(Job job) {
		return false;
	}

}
