package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
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

	@SelectProvider(type = ProjectProvider.class, method = "fetchById")
	Project fetchById(int projectId);

	@InsertProvider(type = ProjectProvider.class, method = "createProject")
	int createProject(Project project);

	class ProjectProvider {

		public String fetchAll() {
			return new SQL().SELECT("*").FROM(Project.getTableName()).toString();
		}

		public String fetchById() {
			return new SQL().SELECT("*").FROM(Project.getTableName()).WHERE("project_id = #{projectId}").toString();
		}

		public String createProject(Project project) {
			return new SQL() {
				{
					INSERT_INTO(Project.getTableName());
					VALUES("project_name", "#{projectName}");
					VALUES("project_desc", "#{projectDesc}");
					VALUES("create_date", "#{createDate}");
					VALUES("update_date", "#{updateDate}");
				}
			}.toString();
		}

	}
}
