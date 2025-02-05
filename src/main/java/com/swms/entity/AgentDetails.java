package com.swms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agent_details")
public class AgentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "agent_name")
	private String name;
	
	@Column(name = "agent_email")
	private String email;
	
	@Column(name = "contact_no")
	private String contactNo;
	
	@OneToMany(mappedBy = "agent",cascade = CascadeType.ALL)
	private List<Address> agentAddress;
	
}
