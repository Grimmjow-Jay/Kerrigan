package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.jay.kerrigan.common.entity.table.Project;

@Mapper
public interface ProjectMapper {

	@Results({ @Result(property = "projectId", column = "project_id"),
			@Result(property = "jobs", column = "project_id", many = @Many(select = "com.jay.kerrigan.master.mapper.JobMapper.fetchByProjectId")) })
	@SelectProvider(type = ProjectProvider.class, method = "fetchAll")
	List<Project> fetchAll();

	class ProjectProvider {

		public String fetchAll() {

			return new SQL().SELECT("*").FROM(Project.getTableName()).toString();
		}

	}
}
