package com.jay.kerrigan.master.service;

import java.util.List;

import com.jay.kerrigan.common.entity.mapper.Project;

public interface ProjectService {

	List<Project> fetchAllProjects();

	String createProject(Project project);

	boolean updateProject(Project project);

	boolean deleteProject(Project project);
}
