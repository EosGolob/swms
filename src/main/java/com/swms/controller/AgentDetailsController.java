package com.swms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swms.dto.AgentDetailsDTO;
import com.swms.entity.AgentDetails;
import com.swms.error.ErrorResponse;
import com.swms.serviceImpl.AgentServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/agent")
public class AgentDetailsController {

//	private static final Logger logger= LoggerFactory.getLogger(AgentDetailsController.class);

	private AgentServiceImpl agentServiceImpl;

	public AgentDetailsController(AgentServiceImpl agentServiceImpl) {
		super();
		this.agentServiceImpl = agentServiceImpl;
	}

	@PostMapping("/agentInformation")
	public ResponseEntity<?> createAgentInformation(@Valid @RequestBody AgentDetailsDTO agentDetailsDTO,
			BindingResult bindingResult) {
		log.info("Received request to create agent information : {} ", agentDetailsDTO);
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());
			log.error("Validation failed for agent information :{}", errorMessages);
			ErrorResponse errorResponse = new ErrorResponse(errorMessages);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		log.info("Validation passed.Proceeding with agent creation for : {} ", agentDetailsDTO);
		AgentDetails agentDetails = agentServiceImpl.createAgentWithAddresses(agentDetailsDTO);
		log.info("Agent Created successfully: {}", agentDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(agentDetails);
	}

}
