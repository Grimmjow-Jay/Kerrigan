package com.jay.kerrigan.master.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
