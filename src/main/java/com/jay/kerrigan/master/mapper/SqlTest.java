package com.jay.kerrigan.master.mapper;

import org.apache.ibatis.jdbc.SQL;

public class SqlTest {

	public static void main(String[] args) {
		SQL sql = new SQL();
		sql.SELECT("p.*", "j.job_id", "j.job_name", "j.job_desc", "j.create_date job_create_date",
				"j.update_date job_update_date");
		sql.FROM("t_project p", "t_job j");
		sql.WHERE("p.project_id = j.fk_project_id");
		System.out.println(sql.toString());
	}

}
