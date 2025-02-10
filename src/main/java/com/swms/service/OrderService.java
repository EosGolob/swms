package com.swms.service;

import com.swms.dto.OrdersDTO;
import com.swms.entity.Orders;

public interface OrderService {

	Orders requestOrderDetails(OrdersDTO orderDto);

}
