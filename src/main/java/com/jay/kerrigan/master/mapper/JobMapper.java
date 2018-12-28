package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import com.jay.kerrigan.common.entity.table.Job;

@Mapper
public interface JobMapper {

	@Results({ @Result(property = "jobId", column = "job_id"),
			@Result(property = "project", column = "fk_project_id", one = @One(select = "com.jay.kerrigan.master.mapper.ProjectMapper.fetchById")),
			@Result(property = "flows", column = "job_id", many = @Many(select = "com.jay.kerrigan.master.mapper.FlowMapper.fetchByJobId")) })
	@SelectProvider(type = JobProvider.class, method = "fetchAll")
	List<Job> fetchAll();

	@SelectProvider(type = JobProvider.class, method = "fetchByProjectId")
	List<Job> fetchByProjectId(int projectId);

	@SelectProvider(type = JobProvider.class, method = "fetchByFlowId")
	List<Job> fetchByFlowId(int flowId);

	class JobProvider {
		public String fetchAll() {
			return "SELECT * FROM t_job";
		}

		public String fetchByProjectId() {
			return "SELECT * FROM t_job where fk_project_id = #{projectId}";
		}

		public String fetchByFlowId() {
			return "SELECT * FROM t_job j, t_job_flow jf WHERE j.job_id = jf.fk_job_id AND jf.fk_flow_id = #{flowId}";
		}

	}
}
