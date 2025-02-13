package com.swms.dto;

import java.util.List;

import com.swms.entity.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {

	private Long id;
	
    @NotBlank(message = "Shop Gst no Required")
	private String shopGstId;
	
    @NotBlank(message = "Shop name Required")
	private String shop_name;
	
    @NotBlank(message = "Shop type Required")
	private String type;
	
    @NotBlank(message = "Shop Contact no Required")
	private String contact_info;
	
    @NotEmpty(message = "shop_address address is mandatory")
	@Valid
	private List<AddressDTO> shop_address;
}
