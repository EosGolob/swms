package com.swms.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	
	@NotBlank(message = "product Name is required")
	private String name;
	
//	@NotBlank(message = "product category is required")
	@NotEmpty(message = "Product category cannot be empty")
	private String category;
	
	@NotNull(message = "product quantity is required")
	@Min(value = 0, message = "Product quantity must be zero or positive")
	private long quantity;
	
	@NotBlank(message = "product color is required")
	private String color;
	
	@NotNull(message = "product wholesale_price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Wholesale price must be greater than zero")
	private BigDecimal wholesale_price;
	
	@NotNull(message = "product retail_price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Retail price must be greater than zero")
	private float retail_price;

}
