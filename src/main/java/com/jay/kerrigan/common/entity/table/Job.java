package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Job implements Serializable {

	private static final long serialVersionUID = 6495839915083393117L;

	private Integer jobId;

	@NotBlank(message = "jobName cannot be empty")
	private String jobName;
	private String jobDesc;
	private Date createDate;
	private Date updateDate;
	private Project project;
	private List<Flow> flows;

	public static String getTableName() {
		return "t_job";
	}

	public Job() {
	}

	public Job(String jobName, String jobDesc, Date createDate, Date updateDate, Project project, List<Flow> flows) {
		this.jobName = jobName;
		this.jobDesc = jobDesc;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.project = project;
		this.flows = flows;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobDesc=" + jobDesc + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", project=" + project + ", flows=" + flows + "]";
	}

}
