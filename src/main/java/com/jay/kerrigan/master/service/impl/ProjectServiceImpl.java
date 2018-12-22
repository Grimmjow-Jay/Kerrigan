package com.jay.kerrigan.master.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jay.kerrigan.common.entity.table.Project;
import com.jay.kerrigan.master.repository.ProjectRepository;
import com.jay.kerrigan.master.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> fetchAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public String createProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProject(Project project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProject(Project project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uploadFiles(List<MultipartFile> files, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFiles(List<String> files, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<File> fetchFilesOfProject(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
