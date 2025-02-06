package com.swms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Column(name = "agent_name")
	private String name;

	@NotBlank(message = "Email is mandatory")
	@Column(name = "agent_email")
	private String email;

	@NotBlank(message = "Phone No is mandatory")
	@Column(name = "contact_no")
	private String contactNo;

	@NotBlank
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	@Valid
	private List<Address> agentAddress;

}
