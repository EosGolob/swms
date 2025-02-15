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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	@NotNull
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	@Valid
	private List<Address> agentAddress;

	@Override
	public String toString() {
		return "AgentDetails [id=" + id + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
				+ ", agentAddress=" + agentAddress + "]";
	}

	
}
