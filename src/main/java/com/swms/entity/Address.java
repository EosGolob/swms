package com.swms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotBlank(message = "street Name should not be blank")
	@Column(name = "street")
	private String street;
	
	@NotBlank(message = "city Name should not be blank")
	@Column(name = "city")
	private String city;
	
	@NotBlank(message = "state Name should not be blank")
	@Column(name = "state")
	private String state;
	
	@NotBlank(message = "zipCode Name should not be blank")
	@Column(name = "zip_code")
	private String zipCode;
	
	@NotBlank(message = "country Name should not be blank")
	@Column(name = "country")
	private String country;
	
	@ManyToOne
    @JoinColumn(name = "shop_id")
	@JsonIgnore
    private Shops shop;
	
	@ManyToOne
	@JoinColumn(name = "angent_id")
	@JsonIgnore
	private AgentDetails agent;
}
