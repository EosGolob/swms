package com.swms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {
  
	private Long productId;
	private Long quantity;
	private float price;
}
