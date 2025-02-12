package com.swms.service;

import com.swms.dto.OrderRequestDTO;
import com.swms.dto.OrdersDTO;
import com.swms.entity.Orders;

public interface OrderService {

	Orders requestOrderDetails(OrdersDTO orderDto);
	Orders createOrderRequest(OrderRequestDTO orderRequest);
	}
