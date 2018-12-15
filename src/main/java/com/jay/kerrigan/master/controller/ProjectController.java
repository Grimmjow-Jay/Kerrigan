package com.jay.kerrigan.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.entity.mapper.Project;
import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/fetch_all")
	public ResponseModel<List<Project>> fetchAllProjects() {
		return ResponseModel.success(projectService.fetchAllProjects());
	}
}
