package com.swms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shops")
public class Shops {
	@Id
	private Long id;
	
	@Column(name = "shop_gst_id")
	private String shop_gst_id;
	
	@Column(name = "shop_name")
	private String shop_name;
	
	@Column(name = "shope_type")
	private String type;
	
	@Column(name = "shop_contact_info")
	private String contact_info;
	
//	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
//	private List<Address> shop_address;
}
