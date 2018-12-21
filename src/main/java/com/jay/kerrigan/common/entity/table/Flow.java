package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_flow")
public class Flow implements Serializable {

	private static final long serialVersionUID = 2865836727598476424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String flowId;

	private String flowName;
	private String flowDesc;
	private Date createDate;
	private Date updateDate;

	@ManyToMany
	private List<Job> jobs;
}
