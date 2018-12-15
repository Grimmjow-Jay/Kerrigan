package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jay.kerrigan.common.entity.mapper.Project;

@Mapper
public interface ProjectMapper {

	List<Project> fetchAllProjects();

}
