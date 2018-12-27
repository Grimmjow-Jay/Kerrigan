package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Flow implements Serializable {

	private static final long serialVersionUID = 2865836727598476424L;

	private Integer flowId;

	@NotBlank(message = "flowName cannot be empty")
	private String flowName;
	private String flowDesc;
	private Date createDate;
	private Date updateDate;

	private List<Job> jobs;

	public static String getTableName() {
		return "t_flow";
	}

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
