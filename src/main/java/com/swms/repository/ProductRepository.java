package com.swms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

	 Optional<Products> findByProductId(Long productId);

}
