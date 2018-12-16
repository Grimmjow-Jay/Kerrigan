package com.jay.kerrigan.common.entity.mapper;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class Project implements Serializable {
	private static final long serialVersionUID = -743520012346990221L;

	protected String projectId;

	@NotNull(message = "projectName cannot be empty")
	protected String projectName;
	protected String projectDesc;
	protected Date createDate;
	protected Date updateDate;

	public static String getTableName() {
		return "t_project";
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
