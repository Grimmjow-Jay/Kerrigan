package com.jay.kerrigan.master.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

	@Override
	public String createProject(Project project) {
		String projectId = UUID.randomUUID().toString().replace("-", "");
		project.setProjectId(projectId);
		project.setCreateDate(new Date());
		project.setUpdateDate(project.getCreateDate());
		projectMapper.createProject(project);

		return projectId;
	}

	@Override
	public boolean updateProject(Project project) {
		project.setUpdateDate(new Date());
		return projectMapper.updateProject(project) == 1;
	}

	@Override
	public boolean deleteProject(Project project) {
		return projectMapper.deleteProject(project) == 1;
	}
}
