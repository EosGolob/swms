package com.swms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shops")
public class Shops {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Shop Gst no Required")
	@Column(name = "shop_gst_id")
	private String shop_gst_id;
	
	@NotBlank(message = "Shop name Required")
	@Column(name = "shop_name")
	private String shop_name;
	
	@NotBlank(message = "Shop type Required")
	@Column(name = "shope_type")
	private String type;
	
	@NotBlank(message = "Shop Contact no Required")
	@Column(name = "shop_contact_info")
	private String contact_info;
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
	private List<Address> shop_address;
}
