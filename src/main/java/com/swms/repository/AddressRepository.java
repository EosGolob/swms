package com.swms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
