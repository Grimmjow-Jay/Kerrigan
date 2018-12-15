package com.jay.kerrigan.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.kerrigan.common.entity.mapper.Project;
import com.jay.kerrigan.master.mapper.ProjectMapper;
import com.jay.kerrigan.master.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> fetchAllProjects() {
		return projectMapper.fetchAllProjects();
	}

}
