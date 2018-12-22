package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "t_job")
public class Job implements Serializable {

	private static final long serialVersionUID = 6495839915083393117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobId;

	@NotBlank(message = "jobName cannot be empty")
	private String jobName;
	private String jobDesc;
	private Date createDate;
	private Date updateDate;

	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonBackReference // 防止对象的递归访问
	private Project project;

	@ManyToMany(mappedBy = "jobs")
	private List<Flow> flows;

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
