package com.jay.kerrigan.master.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jay.kerrigan.common.entity.table.Project;
import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.ProjectService;

@RestController
@RequestMapping("/project")
@Validated
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/fetch_all")
	public ResponseModel<List<Project>> fetchAllProjects() {
		return ResponseModel.success(projectService.fetchAllProjects());
	}

	@RequestMapping("/create_project")
	public ResponseModel<String> createProject(@Validated Project project) {
		return ResponseModel.success(projectService.createProject(project));
	}

	@RequestMapping("/upload_files")
	public ResponseModel<Boolean> uploadFiles(List<MultipartFile> files,
			@NotEmpty(message = "projectId cannot be empty") String projectId) {
		return ResponseModel.success(projectService.uploadFiles(files, projectId));
	}

	@RequestMapping("/remove_files")
	public ResponseModel<Boolean> removeFiles(List<String> files,
			@NotEmpty(message = "projectId cannot be empty") String projectId) {
		return ResponseModel.success(projectService.removeFiles(files, projectId));
	}

}
