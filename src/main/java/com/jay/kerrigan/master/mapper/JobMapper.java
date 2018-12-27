package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.jay.kerrigan.common.entity.table.Job;

@Mapper
public interface JobMapper {

	@SelectProvider(type = JobProvider.class, method = "fetchAll")
	List<Job> fetchAll();

	@SelectProvider(type = JobProvider.class, method = "fetchByProjectId")
	List<Job> fetchByProjectId(int projectId);

	class JobProvider {
		public String fetchAll() {
			return "SELECT * FROM t_job";
		}

		public String fetchByProjectId() {
			return "SELECT * FROM t_job where fk_project_id = #{projectId}";
		}

	}
}
