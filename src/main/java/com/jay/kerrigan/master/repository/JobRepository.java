package com.jay.kerrigan.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.kerrigan.common.entity.table.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
