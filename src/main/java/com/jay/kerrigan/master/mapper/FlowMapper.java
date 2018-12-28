package com.jay.kerrigan.master.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.jay.kerrigan.common.entity.table.Flow;

@Mapper
public interface FlowMapper {

	@Results({ @Result(property = "flowId", column = "flow_id"),
			@Result(property = "jobs", column = "flow_id", many = @Many(select = "com.jay.kerrigan.master.mapper.JobMapper.fetchByFlowId")) })
	@SelectProvider(type = FlowProvider.class, method = "fetchAll")
	List<Flow> fetchAll();

	@SelectProvider(type = FlowProvider.class, method = "fetchByJobId")
	List<Flow> fetchByJobId(int jobId);

	class FlowProvider {

		public String fetchAll() {
			return new SQL().SELECT("*").FROM(Flow.getTableName()).toString();
		}

		public String fetchByJobId() {
			return "SELECT * FROM t_flow f,t_job_flow jf WHERE f.flow_id = jf.fk_flow_id AND jf.fk_job_id = #{jobId}";
		}
	}

}
