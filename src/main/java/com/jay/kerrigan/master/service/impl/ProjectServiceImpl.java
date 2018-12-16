package com.jay.kerrigan.master.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jay.kerrigan.common.config.KerriganEnv;
import com.jay.kerrigan.common.entity.ProjectDetail;
import com.jay.kerrigan.common.entity.mapper.Project;
import com.jay.kerrigan.common.exception.KerriganException;
import com.jay.kerrigan.common.util.FileUtil;
import com.jay.kerrigan.common.util.UUIDUtil;
import com.jay.kerrigan.master.mapper.ProjectMapper;
import com.jay.kerrigan.master.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final String SEP = File.separator;

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> fetchAllProjects() {
		List<Project> fetchAllProjects = projectMapper.fetchAllProjects();
		List<Project> result = new ArrayList<>();
		for (Project project : fetchAllProjects) {
			ProjectDetail newProject = new ProjectDetail(project);
			List<String> fileNames = new ArrayList<>();
			fetchFilesOfProject(project.getProjectId()).forEach(f -> fileNames.add(f.getName()));
			newProject.setFiles(fileNames);
			result.add(newProject);
		}
		return result;

	}

	@Override
	public String createProject(Project project) {
		project.setProjectId(UUIDUtil.randomUUID32());
		project.setCreateDate(new Date());
		project.setUpdateDate(project.getCreateDate());
		projectMapper.createProject(project);

		return project.getProjectId();
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

	@Override
	public boolean uploadFiles(List<MultipartFile> files, String projectId) {
		KerriganException.assertNotEmpty(files, "Files cannot be empty.");
		Project project = projectMapper.fetchProjectById(projectId);
		KerriganException.assertNotNull(project, "Project[projectId: " + projectId + "] not exist.");

		String projectPath = KerriganEnv.PROJECTS_PATH + SEP + projectId;

		for (MultipartFile multipartFile : files) {
			if (multipartFile == null || multipartFile.isEmpty()) {
				throw new KerriganException("File cannot be empty.");
			}
			File file = new File(projectPath + SEP + multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(file);
			} catch (Exception e) {
				throw new KerriganException("Save file[" + multipartFile.getOriginalFilename() + "] failed.");
			}
		}
		return true;
	}

	@Override
	public boolean removeFiles(List<String> files, String projectId) {
		KerriganException.assertNotEmpty(files, "Files cannot be empty.");
		Project project = projectMapper.fetchProjectById(projectId);
		KerriganException.assertNotNull(project, "Project[projectId: " + projectId + "] not exist.");

		List<File> deleteFiles = new ArrayList<>();
		for (String fileName : files) {
			File file = new File(KerriganEnv.PROJECTS_PATH + SEP + projectId + SEP + fileName);
			if (!file.exists()) {
				throw new KerriganException("File[" + fileName + "] not exist.");
			}
			deleteFiles.add(file);
		}
		return FileUtil.deleteRecursively(deleteFiles);
	}

	@Override
	public List<File> fetchFilesOfProject(String projectId) {
		Project project = projectMapper.fetchProjectById(projectId);
		KerriganException.assertNotNull(project, "Project[projectId: " + projectId + "] not exist.");

		File projectPath = new File(KerriganEnv.PROJECTS_PATH + SEP + projectId);
		if (!projectPath.exists()) {
			throw new KerriganException("File of project[id: " + project.getProjectId() + ", name: "
					+ project.getProjectName() + "] does not exist.");
		}
		return new ArrayList<>(Arrays.asList(projectPath.listFiles()));
	}

}
