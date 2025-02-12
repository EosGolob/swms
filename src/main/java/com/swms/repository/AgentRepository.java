package com.swms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swms.entity.AgentDetails;

@Repository
public interface AgentRepository extends JpaRepository<AgentDetails, Long> {
	Optional<AgentDetails> findById(Long agentId);

	Optional<AgentDetails> findByEmail(String email);

	Optional<AgentDetails> findByContactNo(String contactNo);

	Optional<AgentDetails> findByName(String name);

	AgentDetails findByEmailAndContactNo(String agentEmail, String agentContact);
}
