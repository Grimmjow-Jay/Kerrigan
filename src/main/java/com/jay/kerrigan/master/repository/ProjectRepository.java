package com.jay.kerrigan.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.kerrigan.common.entity.table.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
