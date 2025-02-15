package com.swms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swms.entity.Products;
import com.swms.entity.Shops;

public interface ShopRepository extends JpaRepository<Shops, Long> {

	Optional<Shops>  findByShopGstId(String gstId);

	@Query(value = "SELECT s.id, s.shop_contact_info , s.shop_gst_id , " +
            "s.shop_name , s.shope_type , " +
            "a.city , a.country , a.state , a.street , a.zip_code  " +
            "FROM shops s " +
            "JOIN address a ON s.id = a.shop_id " +
            "WHERE s.shop_gst_id = :gstId", nativeQuery = true)
	 Optional<Shops> findShopWithAddressByGstId(String gstId);
}
