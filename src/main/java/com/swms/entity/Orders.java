package com.swms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	
	@Column(name = "Shop_gst_id")
	private String shop_gst_id; 
	
	/**
	@Column(name = "product_id")
	private String product_id;
	*/
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "paymentStatus")
	private String paymentStatus;
	
	@Column(name = "order_status")
	private String order_status;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate; 
	
//	@Version
//	private Long version;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")  // Join with the product table
	private Products product; 
	
	@ManyToOne
    @JoinColumn(name = "agent_id")
    private AgentDetails agent;  // To store which agent the buyer came through
   
	
	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shops shop;
	
    /**
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
	*/
	
    /**
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;  
    */
}
