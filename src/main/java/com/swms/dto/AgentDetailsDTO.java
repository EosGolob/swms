package com.swms.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDetailsDTO {
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
    private String email;
	
	@NotBlank(message = "Phone No is mandatory")
    private String contactNo;
    
	@NotEmpty(message = "Agent address is mandatory")
	@Valid
    private List<AddressDTO> agentAddress;
}
