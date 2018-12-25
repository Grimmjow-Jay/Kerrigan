package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Project implements Serializable {
	private static final long serialVersionUID = -743520012346990221L;

	private Integer projectId;

	@NotBlank(message = "projectName cannot be empty")
	private String projectName;
	private String projectDesc;
	private Date createDate;
	private Date updateDate;

	private List<Job> jobs;

	public Project() {
	}

	public Project(String projectName, String projectDesc, Date createDate, Date updateDate, List<Job> jobs) {
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", jobs=" + jobs + "]";
	}

}
