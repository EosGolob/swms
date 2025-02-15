package com.swms.service;

import java.util.List;

import com.swms.dto.OrderRequestDTO;
import com.swms.dto.OrdersDTO;
import com.swms.entity.Orders;

public interface OrderService {

	Orders requestOrderDetails(OrdersDTO orderDto);
	List<Orders> createOrderRequest(OrderRequestDTO orderRequest);
	}
