package com.jay.kerrigan.master.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.kerrigan.common.entity.table.Project;

public interface ProjectDao extends JpaRepository<Project, String> {

}
