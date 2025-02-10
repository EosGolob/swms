package com.swms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
