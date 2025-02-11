package com.swms.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "product_id") 
	private Long productId;
	
	@NotBlank(message = "product Name is required")
	@Column(name = "product_name")
	private String name;
	
//	@NotNull(message = "product category is required")
	@NotEmpty(message = "Product category cannot be empty")
	@Column(name = "product_category")
	private String category;
	
	@NotNull(message = "product quantity is required")
	@Min(value = 0, message = "Product quantity must be zero or positive")
	@Column(name = "product_quantity")
	private long quantity;
	
	@NotBlank(message = "product color is required")
	@Column(name = "product_color")
	private String color;
	
	@NotNull(message = "product wholesale_price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Wholesale price must be greater than zero")
	@Column(name = "product_wholesale_price")
	private BigDecimal wholesale_price;
	
	@NotNull(message = "product retail_price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Retail price must be greater than zero")
	@Column(name = "product_retail_price")
	private float retail_price;
	
	/**
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;  
    */
}
