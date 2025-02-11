package com.swms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swms.dto.OrderDetailsRequestDto;
import com.swms.dto.OrdersDTO;
import com.swms.entity.OrderProduct;
import com.swms.entity.Orders;
import com.swms.error.ErrorResponse;
import com.swms.serviceImpl.OrderServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("api/order")
public class OrderController {
	
	private OrderServiceImpl orderServiceImpl;
		
	public OrderController(OrderServiceImpl orderServiceImpl) {
		super();
		this.orderServiceImpl = orderServiceImpl;
	}

	@PostMapping("/orderInformation")
	public ResponseEntity<?> order(@Valid @RequestBody OrdersDTO ordersDTO,BindingResult bindingResult) {
		
		log.info("Received request to create order information : {} ",ordersDTO);
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());
			log.error("Validation failed for agent information :{}", errorMessages);
			ErrorResponse errorResponse = new ErrorResponse(errorMessages);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		log.info("Validation passed.Proceeding with order creation for : {} ", ordersDTO);
		Orders  orderDto  = orderServiceImpl.requestOrderDetails(ordersDTO);
		log.info("Order Created successfully: {}", orderDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
	}
	
	 @PostMapping("/create")
	    public ResponseEntity<?> createOrder(@RequestBody OrderDetailsRequestDto orderDetails) {
	        Orders newOrder = orderServiceImpl.createOrder(orderDetails);
	        if (newOrder != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	  }

}
