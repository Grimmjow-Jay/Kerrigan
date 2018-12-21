package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_job")
public class Job implements Serializable {

	private static final long serialVersionUID = 6495839915083393117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String jobId;

	@NotNull(message = "jobName cannot be empty")
	private String jobName;
	private String jobDesc;
	private Date createDate;
	private Date updateDate;

	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonBackReference // 防止对象的递归访问
	private Project project;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_flow", joinColumns = { @JoinColumn(name = "job_id") }, inverseJoinColumns = {
			@JoinColumn(name = "flow_id") })
	private List<Flow> flows;
	
	

}
