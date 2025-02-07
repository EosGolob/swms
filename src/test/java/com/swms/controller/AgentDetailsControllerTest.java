package com.swms.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swms.dto.AddressDTO;
import com.swms.dto.AgentDetailsDTO;
import com.swms.entity.Address;
import com.swms.entity.AgentDetails;
import com.swms.serviceImpl.AgentServiceImpl;

public class AgentDetailsControllerTest {

	@InjectMocks
	private AgentDetailsController agentDetailsController;

	@Mock
	private AgentServiceImpl agentServiceImpl;

	@Mock
	private BindingResult bindingResult;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(agentDetailsController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testCreateAgentInformation_Success() throws Exception {
		
		List<AddressDTO> addressDTO = new ArrayList<>();
		List<Address> address = new ArrayList<>();
		
		AddressDTO addDTO = new AddressDTO();
		addDTO.setCity("Test city");
		addDTO.setCountry("Test County");
		addDTO.setState("Test State");
		addDTO.setStreet("Test Street");
		addDTO.setZipCode("88888");
		addressDTO.add(addDTO);
		address.addAll(address);
			
		AgentDetailsDTO agentDetailsDTO = new AgentDetailsDTO();
		agentDetailsDTO.setName("Test Agent");
		agentDetailsDTO.setEmail("test@gmail.com");
		agentDetailsDTO.setContactNo("6666666");
		agentDetailsDTO.setAgentAddress(addressDTO);

		AgentDetails mockAgentDetails = new AgentDetails();
		mockAgentDetails.setName("Test Agent");
		mockAgentDetails.setEmail("test@gmail.com");
		mockAgentDetails.setContactNo("6666666");
		mockAgentDetails.setAgentAddress(address);
		
		

		when(agentServiceImpl.createAgentWithAddresses(agentDetailsDTO)).thenReturn(mockAgentDetails);
		when(bindingResult.hasErrors()).thenReturn(false);

		mockMvc.perform(post("/api/agent/agentInformation").contentType("application/json")
				.content(objectMapper.writeValueAsString(agentDetailsDTO))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Test Agent"))
				.andExpect(jsonPath("$.email").value("test@gmail.com"));

		verify(agentServiceImpl, times(1)).createAgentWithAddresses(agentDetailsDTO);

	}
	
	  @Test
	    public void testCreateAgentInformation_ValidationFailure() throws Exception {
	        AgentDetailsDTO agentDetailsDTO = new AgentDetailsDTO();
	        agentDetailsDTO.setName(""); 

	        List<ObjectError> errors = Collections.singletonList(new ObjectError("name", "Name is required"));
	        when(bindingResult.hasErrors()).thenReturn(true);
	        when(bindingResult.getAllErrors()).thenReturn(errors);

	        mockMvc.perform(post("/api/agent/agentInformation")
	                        .contentType("application/json")
	                        .content(objectMapper.writeValueAsString(agentDetailsDTO)))
	                .andExpect(status().isBadRequest())
	                .andExpect(jsonPath("$.errors[0]").value("Name is required"));
	        
	        verify(agentServiceImpl, times(0)).createAgentWithAddresses(agentDetailsDTO);
	    }
}
