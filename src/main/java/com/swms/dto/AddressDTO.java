package com.swms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	    @NotBlank(message = "Street Name should not be blank")
	    private String street;
	    
	    @NotBlank(message = "city Name should not be blank")
	    private String city;
	    
	    @NotBlank(message = "state Name should not be blank")
	    private String state;
	    
	    @NotBlank(message = "zipCode Name should not be blank")
	    private String zipCode;
	    
	    @NotBlank(message = "country Name should not be blank")
	    private String country;
}
