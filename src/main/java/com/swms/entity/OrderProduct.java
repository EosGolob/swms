package com.swms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;  // Link to the order

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;  // Link to the product

    @Column(name = "quantity")
    private Long quantity;  // The quantity of the product in the order

    @Column(name = "price")
    private float price;  // The price of the product in the order
}
