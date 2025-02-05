package com.swms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Orders {
	
	@Id
	private Long order_id;
	
	@Column(name = "Shop_gst_id")
	private String shop_gst_id; 
	
	@Column(name = "product_id")
	private String product_id;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "order_status")
	private String order_status;
}
