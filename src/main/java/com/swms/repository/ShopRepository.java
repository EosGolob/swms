package com.swms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Shops;

public interface ShopRepository extends JpaRepository<Shops, Long> {

}
