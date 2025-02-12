package com.swms.service;

import com.swms.dto.AgentDetailsDTO;
import com.swms.entity.AgentDetails;

public interface AgentService {

	AgentDetails createAgentWithAddresses(AgentDetailsDTO agentDetailsDTO);
	
//	AgentDetails getAgent(Long agentId);

}
