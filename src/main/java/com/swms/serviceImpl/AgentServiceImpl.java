package com.swms.serviceImpl;

import org.springframework.stereotype.Service;

import com.swms.entity.AgentDetails;
import com.swms.repository.AgentRepository;
import com.swms.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {
	
	AgentRepository agentRepository;
	

	public AgentServiceImpl(AgentRepository agentRepository) {
		super();
		this.agentRepository = agentRepository;
	}


	@Override
	public AgentDetails createAgent(AgentDetails entity) {
		AgentDetails agentDetails = agentRepository.save(entity);
		return agentDetails;
	}

}
