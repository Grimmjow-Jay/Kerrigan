package com.jay.kerrigan.master.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jay.kerrigan.common.entity.table.Project;

public interface ProjectService {

	List<Project> fetchAll();

	Integer createProject(Project project);

	boolean updateProject(Project project);

	boolean deleteProject(Project project);

	boolean uploadFiles(List<MultipartFile> files, Integer projectId);

	boolean removeFiles(List<String> files, Integer projectId);

	List<File> fetchFilesOfProject(Integer projectId);
}
