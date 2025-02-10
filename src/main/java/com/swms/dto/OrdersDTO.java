package com.swms.dto;

import java.time.LocalDateTime;

import com.swms.entity.AgentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO { 
	
    private Long order_id;
	
	private String shop_gst_id; 
	
	private String product_id;
	
	private Long quantity;
	
	private float price;
	
	private String paymentStatus;
	
	private String order_status;
	
	private LocalDateTime orderDate; 

//    private AgentDetails agent;  
}
