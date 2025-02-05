package com.swms.entity;

import java.text.DecimalFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Products {

	@Id
	private Long product_id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_category")
	private String category;
	
	@Column(name = "product_quantity")
	private long quantity;
	
	@Column(name = "product_color")
	private String color;
	
	@Column(name = "product_wholesale_price")
	private DecimalFormat wholesale_price;
	
	@Column(name = "product_retail_price")
	private float retail_price;
	
}
