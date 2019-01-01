package com.jay.kerrigan.master.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jay.kerrigan.common.entity.table.Flow;
import com.jay.kerrigan.master.service.FlowService;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {

	@Override
	public boolean createFlow(Flow flow) {
		return false;
	}

}
