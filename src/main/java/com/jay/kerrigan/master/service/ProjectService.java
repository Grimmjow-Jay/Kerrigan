package com.jay.kerrigan.master.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jay.kerrigan.common.entity.table.Project;

public interface ProjectService {

	List<Project> fetchAll();

	String createProject(Project project);

	boolean updateProject(Project project);

	boolean deleteProject(Project project);

	boolean uploadFiles(List<MultipartFile> files, String projectId);

	boolean removeFiles(List<String> files, String projectId);

	List<File> fetchFilesOfProject(String projectId);
}
