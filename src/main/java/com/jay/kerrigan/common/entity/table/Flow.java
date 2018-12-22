package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "t_flow")
public class Flow implements Serializable {

	private static final long serialVersionUID = 2865836727598476424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flowId;

	@NotBlank(message = "flowName cannot be empty")
	private String flowName;
	private String flowDesc;
	private Date createDate;
	private Date updateDate;

	@ManyToMany
	@JoinTable(name = "t_flow_job", joinColumns = { @JoinColumn(name = "flow_id") }, inverseJoinColumns = {
			@JoinColumn(name = "job_id") })
	private List<Job> jobs;

	public Flow() {
	}

	public Flow(String flowName, String flowDesc, Date createDate, Date updateDate, List<Job> jobs) {
		this.flowName = flowName;
		this.flowDesc = flowDesc;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Flow [flowId=" + flowId + ", flowName=" + flowName + ", flowDesc=" + flowDesc + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", jobs=" + jobs + "]";
	}

}
