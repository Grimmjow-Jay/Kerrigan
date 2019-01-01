package com.jay.kerrigan.master.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jay.kerrigan.common.config.KerriganEnv;
import com.jay.kerrigan.common.entity.table.Project;
import com.jay.kerrigan.common.exception.KerriganException;
import com.jay.kerrigan.common.util.FileUtil;
import com.jay.kerrigan.master.mapper.ProjectMapper;
import com.jay.kerrigan.master.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> fetchAll() {
		List<Project> fetchAll = projectMapper.fetchAll();
		return fetchAll;
	}

	@Override
	public Integer createProject(Project project) {
		Date now = new Date();
		project.setCreateDate(now);
		project.setUpdateDate(now);
		projectMapper.createProject(project);
		return project.getProjectId();
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
	public boolean uploadFiles(List<MultipartFile> files, Integer projectId) {
		KerriganException.assertNotEmpty(files, "There isn't any file found");

		Project project = projectMapper.fetchById(projectId);
		KerriganException.assertNotNull(project, "There isn't a project with id:[" + projectId + "]");

		files.forEach(e -> {
			KerriganException.assertCondition(e.isEmpty(), "File:[" + e.getOriginalFilename() + "] is empty");
		});

		String storePath = KerriganEnv.PROJECTS_PATH + File.separator + projectId + File.separator;
		List<File> uploadedFile = Lists.newArrayList();
		for (MultipartFile multipartFile : files) {
			File dest = new File(storePath + multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(dest);
				uploadedFile.add(dest);
			} catch (IllegalStateException | IOException e) {
				log.error("Project:[" + project.getProjectName() + "] upload file exception: [" + e.getMessage() + "]");
				// If exception caught, uploaded files should be deleted
				FileUtil.deleteRecursively(uploadedFile);
				throw new KerriganException("Upload file error:[" + e.getMessage() + "]");
			}
		}
		log.info("Project:[" + project.getProjectName() + "] uploaded files:[" + uploadedFile + "]");
		return true;
	}

	@Override
	public boolean removeFiles(List<String> files, Integer projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<File> fetchFilesOfProject(Integer projectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
