package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.jay.kerrigan.common.entity.mapper.Project;

@Mapper
public interface ProjectMapper {

	@SelectProvider(method = "fetchAllProjects", type = ProjectProvider.class)
	List<Project> fetchAllProjects();

	@InsertProvider(method = "createProject", type = ProjectProvider.class)
	int createProject(Project project);

	@UpdateProvider(method = "updateProject", type = ProjectProvider.class)
	int updateProject(Project project);

	@UpdateProvider(method = "deleteProject", type = ProjectProvider.class)
	int deleteProject(Project project);

	@SelectProvider(method = "fetchProjectById", type = ProjectProvider.class)
	Project fetchProjectById(String projectId);

	class ProjectProvider {

		public String fetchAllProjects() {
			return new SQL().SELECT("*").FROM(Project.getTableName()).toString();
		}

		public String fetchProjectById() {
			return new SQL().SELECT("*").FROM(Project.getTableName()).WHERE("project_id=#{projectId}").toString();
		}

		public String createProject(Project project) {
			return new SQL() {
				{
					INSERT_INTO(Project.getTableName());
					VALUES("project_id", "#{projectId}");
					VALUES("project_name", "#{projectName}");
					VALUES("project_desc", "#{projectDesc}");
					VALUES("create_date", "#{createDate}");
					VALUES("update_date", "#{updateDate}");
				}
			}.toString();
		}

		public String updateProject(Project project) {
			return new SQL() {
				{
					UPDATE(Project.getTableName());
					if (StringUtils.isBlank(project.getProjectName())) {
						SET("project_name=#{projectName}");
					}
					if (StringUtils.isBlank(project.getProjectDesc())) {
						SET("project_desc=#{projectDesc}");
					}
					SET("update_date=#{updateDate}");
					WHERE("project_id=#{projectId}");
				}
			}.toString();
		}

		public String deleteProject(Project project) {
			return new SQL() {
				{
					DELETE_FROM(Project.getTableName());
					WHERE("project_id=#{projectId}");
				}
			}.toString();
		}
	}

}
