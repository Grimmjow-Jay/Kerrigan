package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "t_project")
public class Project implements Serializable {
	private static final long serialVersionUID = -743520012346990221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

	@NotBlank(message = "projectName cannot be empty")
	@Column(unique = true)
	private String projectName;
	private String projectDesc;
	private Date createDate;
	private Date updateDate;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
