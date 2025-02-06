package com.swms.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swms.dto.AgentDetailsDTO;
import com.swms.entity.Address;
import com.swms.entity.AgentDetails;
import com.swms.repository.AgentRepository;
import com.swms.service.AgentService;

import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

	private final AgentRepository agentRepository;

//	private final AddressRepository addressRepository;
//	AddressRepository addressRepository,
	private final ModelMapper modelMapper;

	@Autowired
	public AgentServiceImpl(AgentRepository agentRepository, 
			ModelMapper modelMapper) {
		super();
		this.agentRepository = agentRepository;
//		this.addressRepository = addressRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public AgentDetails createAgentWithAddresses(AgentDetailsDTO agentDetailsDTO) {
		AgentDetails agentDetails = modelMapper.map(agentDetailsDTO, AgentDetails.class);
		List<Address> addresses = agentDetailsDTO.getAgentAddress().stream().map(addressDTO -> {
			Address address = modelMapper.map(addressDTO, Address.class);
			address.setAgent(agentDetails);
			return address;
		}).toList();
		agentDetails.setAgentAddress(addresses);
		AgentDetails savedAgent = agentRepository.save(agentDetails);
		return savedAgent;
	}

}
