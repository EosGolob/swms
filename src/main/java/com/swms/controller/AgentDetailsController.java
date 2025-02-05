package com.swms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swms.entity.Address;
import com.swms.entity.AgentDetails;
import com.swms.serviceImpl.AgentServiceImpl;

@RestController
@RequestMapping("api/agent")
public class AgentDetailsController {


	private AgentServiceImpl agentServiceImpl;

	public AgentDetailsController(AgentServiceImpl agentServiceImpl) {
		super();
		this.agentServiceImpl = agentServiceImpl;
	}

	@PostMapping("/agentInformation")
	public ResponseEntity<AgentDetails> createAgentInformation(@RequestBody AgentDetails entity) {
		AgentDetails agentDetails = agentServiceImpl.createAgent(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(agentDetails);
	}

}
