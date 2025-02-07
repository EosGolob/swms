package com.swms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
