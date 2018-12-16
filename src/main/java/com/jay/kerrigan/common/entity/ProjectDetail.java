package com.jay.kerrigan.common.entity;

import java.util.List;

import com.jay.kerrigan.common.entity.mapper.Project;

public class ProjectDetail extends Project {

	private static final long serialVersionUID = -1789954965548415039L;

	private List<String> files;

	public ProjectDetail() {
	}

	public ProjectDetail(Project project) {
		this.projectId = project.getProjectId();
		this.projectName = project.getProjectName();
		this.projectDesc = project.getProjectDesc();
		this.createDate = project.getCreateDate();
		this.updateDate = project.getUpdateDate();
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", files=" + files + "]";
	}

}
