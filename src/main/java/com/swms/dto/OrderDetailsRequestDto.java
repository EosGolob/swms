package com.swms.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swms.entity.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequestDto {
	
	    /**shope details*/
	    private String shop_gst_id;
		private String shop_name;
		private String type;
		private String contact_info;	 
		private List<AddressDTO> shop_address;
	    
	    /**orders table*/
	    private String product_shop_gst_id; 		
		private Long product_id;		
		private Long quantity;
		private float price;
		private String paymentStatus;
		private String order_status;
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		private LocalDateTime orderDate; 
		
		
		/**Ageny details required*/
		private String name;		
	    private String email;		
	    private String contactNo;
	    private List<AddressDTO> agentAddress;
	    private List<ProductDTO> products;

}
