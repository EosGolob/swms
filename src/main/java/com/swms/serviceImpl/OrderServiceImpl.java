package com.swms.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.OrdersDTO;
import com.swms.entity.Orders;
import com.swms.repository.OrderRepository;
import com.swms.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {

	
	private final OrderRepository orderRepository;
	
	private final ModelMapper modelMapper;
	
	
	public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
		super();
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
	}


    @Override
	public Orders requestOrderDetails(OrdersDTO orderDto) {
		Orders orders = modelMapper.map(orderDto, Orders.class);
		Orders saveOrder = orderRepository.save(orders);
		return saveOrder;
	}

}
