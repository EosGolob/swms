package com.swms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swms.entity.AgentDetails;

@Repository
public interface AgentRepository extends JpaRepository<AgentDetails, Long> {

}
