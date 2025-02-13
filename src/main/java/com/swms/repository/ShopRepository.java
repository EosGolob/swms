package com.swms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Products;
import com.swms.entity.Shops;

public interface ShopRepository extends JpaRepository<Shops, Long> {

	Optional<Shops>  findByShopGstId(String gstId);
}
